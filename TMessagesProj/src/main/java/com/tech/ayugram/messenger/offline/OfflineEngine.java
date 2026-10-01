package com.tech.ayugram.messenger.offline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.os.Build;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.AyuDatabase;
import com.tech.ayugram.messenger.LocalMessage;
import com.tech.ayugram.messenger.LocalMessageDao;
import com.tech.ayugram.messenger.CachedDialog;
import com.tech.ayugram.messenger.CachedDialogDao;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Keep
public class OfflineEngine {
    private static OfflineEngine instance;
    private final AyuDatabase database;
    private final LocalMessageDao messageDao;
    private final CachedDialogDao dialogDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private long lastConnectivityCheck = 0;
    private boolean cachedOfflineStatus = false;

    private OfflineEngine() {
        database = AyuDatabase.getInstance();
        messageDao = database.localMessageDao();
        dialogDao = database.cachedDialogDao();
    }

    public static synchronized OfflineEngine getInstance() {
        if (instance == null) {
            instance = new OfflineEngine();
        }
        return instance;
    }

    /**
     * §1.3 Проверка статуса соединения
     * Условием оффлайна является:
     * - Отсутствие интернет-соединения на уровне ОС (NetworkCapabilities / ConnectivityManager), ИЛИ
     * - Состояние MTProto-клиента: Connecting, Updating, WaitingForNetwork с таймаутом более 1500 мс
     */
    public boolean isOffline() {
        long now = System.currentTimeMillis();
        if (now - lastConnectivityCheck < 1000) {
            return cachedOfflineStatus;
        }
        lastConnectivityCheck = now;

        // Check OS-level connectivity
        boolean hasNetwork = hasInternetConnection();
        
        // Check MTProto state
        ConnectionsManager connManager = ConnectionsManager.getInstance();
        int mtProtoState = connManager.getConnectionState();
        boolean mtProtoOffline = (mtProtoState == ConnectionsManager.ConnectionStateWaitingForNetwork ||
                                  mtProtoState == ConnectionsManager.ConnectionStateConnecting ||
                                  mtProtoState == ConnectionsManager.ConnectionStateUpdating);

        cachedOfflineStatus = !hasNetwork || mtProtoOffline;
        return cachedOfflineStatus;
    }

    private boolean hasInternetConnection() {
        Context context = ApplicationLoader.getApplicationContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Network network = cm.getActiveNetwork();
            if (network == null) return false;
            NetworkCapabilities caps = cm.getNetworkCapabilities(network);
            return caps != null && caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) &&
                   caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED);
        } else {
            android.net.NetworkInfo info = cm.getActiveNetworkInfo();
            return info != null && info.isConnected();
        }
    }

    // ============ §1.3 Правила сохранения (Interception Hooks) ============

    /**
     * Точка входа 1: Входящие события / Updates
     * При получении UpdateNewMessage, UpdateNewChannelMessage или батча сообщений
     * они немедленно асинхронно сериализуются и записываются/обновляются в таблицу local_messages
     */
    public void onMessageReceived(TLRPC.Message message) {
        if (message == null) return;
        executor.execute(() -> {
            LocalMessage localMsg = convertToLocalMessage(message);
            messageDao.insert(localMsg);
        });
    }

    /**
     * Точка входа 2: Просмотр истории
     * При скролле и получении сообщений с сервера в оперативную память,
     * каждое полученное сообщение транзитом сохраняется в local_messages (UPSERT)
     */
    public void onHistoryReceived(List<TLRPC.Message> messages) {
        if (messages == null || messages.isEmpty()) return;
        executor.execute(() -> {
            for (TLRPC.Message msg : messages) {
                LocalMessage localMsg = convertToLocalMessage(msg);
                messageDao.insert(localMsg);
            }
        });
    }

    /**
     * Точка входа 3: Синхронизация папок
     * При успешном обновлении списка диалогов от сервера результат немедленно
     * перезаписывает кэш в cached_dialogs для соответствующего folder_id
     */
    public void onDialogsUpdated(int folderId, TLRPC.messages_Dialogs dialogs) {
        executor.execute(() -> {
            CachedDialog cached = new CachedDialog();
            cached.user_id = UserConfig.getInstance().userId;
            cached.folder_id = folderId;
            cached.raw_data = serializeDialogs(dialogs);
            cached.updated_at = System.currentTimeMillis();
            dialogDao.insert(cached);
        });
    }

    // ============ §1.3 Правила чтения при отсутствии сети (Fallback) ============

    /**
     * Пагинация сообщений в оффлайне
     * При скролле вверх: SELECT из local_messages без сетевых вызовов
     */
    public List<LocalMessage> getOfflineMessages(long userId, long dialogId, long topicId, long offsetId, int limit) {
        return messageDao.getMessagesForDialog(userId, dialogId, topicId, offsetId, limit);
    }

    public List<LocalMessage> getOfflineMessagesByAuthor(long userId, long dialogId, long fromId, int limit) {
        return messageDao.getMessagesByAuthor(userId, dialogId, fromId, limit);
    }

    public List<LocalMessage> searchOfflineMessages(long userId, long dialogId, String pattern, int limit) {
        return messageDao.searchMessages(userId, dialogId, "%" + pattern + "%", limit);
    }

    public CachedDialog getCachedDialogs(long userId, int folderId) {
        return dialogDao.getDialog(userId, folderId);
    }

    private LocalMessage convertToLocalMessage(TLRPC.Message msg) {
        LocalMessage local = new LocalMessage();
        local.user_id = UserConfig.getInstance().userId;
        local.dialog_id = msg.dialog_id != 0 ? msg.dialog_id : msg.peer_id;
        local.message_id = msg.id;
        local.topic_id = 0; // TODO: extract from reply_to or thread
        local.from_id = msg.from_id;
        local.date = msg.date;
        local.text = msg.message != null ? msg.message : "";
        local.raw_data = serializeMessage(msg);
        local.is_out = msg.out;
        local.is_read = msg.unread_count == 0;
        return local;
    }

    private byte[] serializeMessage(TLRPC.Message msg) {
        // Simple serialization - in production use TLRPC serialization
        try {
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(bos);
            oos.writeObject(msg);
            oos.close();
            return bos.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    private byte[] serializeDialogs(TLRPC.messages_Dialogs dialogs) {
        try {
            java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(bos);
            oos.writeObject(dialogs);
            oos.close();
            return bos.toByteArray();
        } catch (Exception e) {
            return new byte[0];
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}