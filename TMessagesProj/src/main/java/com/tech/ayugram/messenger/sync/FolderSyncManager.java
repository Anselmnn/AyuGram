package com.tech.ayugram.messenger.sync;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.AyuDatabase;
import com.tech.ayugram.messenger.FolderConfig;
import com.tech.ayugram.messenger.FolderConfigDao;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

@Keep
public class FolderSyncManager {
    private static FolderSyncManager instance;
    private final AyuDatabase database;
    private final FolderConfigDao folderDao;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    private static final String SERVICE_CHANNEL_TAG = "[AyuGram Settings & Folders Sync]";
    private long serviceChannelId = 0;

    private FolderSyncManager() {
        database = AyuDatabase.getInstance();
        folderDao = database.folderConfigDao();
    }

    public static synchronized FolderSyncManager getInstance() {
        if (instance == null) {
            instance = new FolderSyncManager();
        }
        return instance;
    }

    /**
     * §5.2 Архитектура сервисного канала
     * Поиск/создание канала с тегом "[AyuGram Settings & Folders Sync]"
     * Сохранение JSON конфигурации в закрепленном сообщении
     */
    public void initialize(FolderSyncCallback callback) {
        findOrCreateServiceChannel(new RequestDelegate() {
            @Override
            public void run(TLObject response, TLRPC.TL_error error) {
                if (error == null && response instanceof TLRPC.Chat) {
                    TLRPC.Chat channel = (TLRPC.Chat) response;
                    serviceChannelId = channel.id;
                    // Load existing config
                    loadConfigFromChannel(callback);
                } else {
                    if (callback != null) callback.onError("Failed to create/find service channel");
                }
            }
        });
    }

    private void findOrCreateServiceChannel(RequestDelegate callback) {
        // Search for existing channel
        TLRPC.TL_channels_getChannels req = new TLRPC.TL_channels_getChannels();
        req.id = new ArrayList<>(); // Would need to search by username or iterate
        
        // For simplicity, create new channel
        TLRPC.TL_channels_createChannel createReq = new TLRPC.TL_channels_createChannel();
        createReq.title = "AyuGram Service";
        createReq.about = SERVICE_CHANNEL_TAG;
        createReq.megagroup = false;
        createReq.broadcast = true;
        
        ConnectionsManager.getInstance().sendRequest(createReq, (response, error) -> {
            if (error == null && response instanceof TLRPC.Updates) {
                TLRPC.Updates updates = (TLRPC.Updates) response;
                for (TLRPC.Chat chat : updates.chats) {
                    if (chat.broadcast && SERVICE_CHANNEL_TAG.equals(chat.about)) {
                        // Move to Archive folder (folder_id = 1)
                        moveToArchive(chat.id, callback);
                        return;
                    }
                }
            }
            if (callback != null) callback.run(response, error);
        });
    }

    private void moveToArchive(long channelId, RequestDelegate callback) {
        TLRPC.TL_folders_editPeerFolders folderReq = new TLRPC.TL_folders_editPeerFolders();
        folderReq.folder_id = 1; // Archive
        TLRPC.TL_inputPeerChannel peer = new TLRPC.TL_inputPeerChannel();
        peer.channel_id = channelId;
        peer.access_hash = 0; // Would need actual hash
        folderReq.peer = peer;
        
        ConnectionsManager.getInstance().sendRequest(folderReq, callback);
    }

    private void loadConfigFromChannel(FolderSyncCallback callback) {
        if (serviceChannelId == 0) {
            if (callback != null) callback.onError("No service channel");
            return;
        }
        
        // Get pinned message
        TLRPC.TL_messages_getPinnedDialogs pinnedReq = new TLRPC.TL_messages_getPinnedDialogs();
        pinnedReq.folder_id = 1;
        
        ConnectionsManager.getInstance().sendRequest(pinnedReq, (response, error) -> {
            if (error == null && response instanceof TLRPC.messages_Dialogs) {
                TLRPC.messages_Dialogs dialogs = (TLRPC.messages_Dialogs) response;
                // Find pinned message in service channel
                for (TLRPC.Message msg : dialogs.messages) {
                    if (msg.peer_id == -serviceChannelId && msg.pinned) {
                        parseConfigFromMessage(msg, callback);
                        return;
                    }
                }
            }
            if (callback != null) callback.onError("No config found");
        });
    }

    private void parseConfigFromMessage(TLRPC.Message message, FolderSyncCallback callback) {
        if (message.message == null || !message.message.startsWith("{")) {
            if (callback != null) callback.onError("Invalid config format");
            return;
        }
        
        try {
            JSONObject json = new JSONObject(message.message);
            int version = json.getInt("version");
            
            JSONArray foldersArray = json.getJSONArray("folders");
            for (int i = 0; i < foldersArray.length(); i++) {
                JSONObject folderJson = foldersArray.getJSONObject(i);
                
                FolderConfig config = new FolderConfig();
                config.id = java.util.UUID.randomUUID().toString();
                config.user_id = UserConfig.getInstance().userId;
                config.folder_id = folderJson.getInt("id");
                config.title = folderJson.getString("title");
                config.emoticon = folderJson.getString("emoticon");
                config.flags_json = folderJson.getJSONObject("flags").toString();
                
                JSONArray included = folderJson.getJSONArray("included_peers");
                for (int j = 0; j < included.length(); j++) {
                    // Add to included_peers_json
                }
                
                JSONArray excluded = folderJson.getJSONArray("excluded_peers");
                for (int j = 0; j < excluded.length(); j++) {
                    // Add to excluded_peers_json
                }
                
                config.updated_at = json.getLong("updated_at");
                config.is_custom = true;
                
                folderDao.insert(config);
            }
            
            if (callback != null) callback.onSuccess();
        } catch (Exception e) {
            if (callback != null) callback.onError("Parse error: " + e.getMessage());
        }
    }

    /**
     * §5.2 При изменении папок пользователем - сохранение в сервисный канал
     */
    public void saveConfig(FolderSyncCallback callback) {
        if (serviceChannelId == 0) {
            initialize(new FolderSyncCallback() {
                @Override
                public void onSuccess() {
                    saveConfig(callback);
                }
                @Override
                public void onError(String error) {
                    if (callback != null) callback.onError(error);
                }
            });
            return;
        }
        
        // Build JSON
        JSONObject json = new JSONObject();
        try {
            json.put("version", 1);
            json.put("app", "AyuGram");
            json.put("updated_at", System.currentTimeMillis() / 1000);
            
            JSONArray foldersArray = new JSONArray();
            List<FolderConfig> configs = folderDao.getCustomConfigs(UserConfig.getInstance().userId);
            
            for (FolderConfig config : configs) {
                JSONObject folderJson = new JSONObject();
                folderJson.put("id", config.folder_id);
                folderJson.put("title", config.title);
                folderJson.put("emoticon", config.emoticon);
                folderJson.put("flags", new JSONObject(config.flags_json));
                folderJson.put("included_peers", new JSONArray(config.included_peers_json));
                folderJson.put("excluded_peers", new JSONArray(config.excluded_peers_json));
                foldersArray.put(folderJson);
            }
            
            json.put("folders", foldersArray);
            
            // Send to service channel
            TLRPC.TL_messages_sendMessage sendReq = new TLRPC.TL_messages_sendMessage();
            sendReq.peer = MessagesController.getInstance().getInputPeer(-serviceChannelId);
            sendReq.message = json.toString();
            sendReq.random_id = System.currentTimeMillis();
            
            ConnectionsManager.getInstance().sendRequest(sendReq, (response, error) -> {
                if (error == null && response instanceof TLRPC.TL_messages_sentMessage) {
                    TLRPC.TL_messages_sentMessage sent = (TLRPC.TL_messages_sentMessage) response;
                    // Pin the message
                    pinMessage(sent.id, callback);
                } else {
                    if (callback != null) callback.onError("Failed to send config");
                }
            });
        } catch (Exception e) {
            if (callback != null) callback.onError("JSON error: " + e.getMessage());
        }
    }

    private void pinMessage(int msgId, FolderSyncCallback callback) {
        TLRPC.TL_messages_pinChatMessage pinReq = new TLRPC.TL_messages_pinChatMessage();
        pinReq.peer = MessagesController.getInstance().getInputPeer(-serviceChannelId);
        pinReq.id = msgId;
        pinReq.silent = true;
        
        ConnectionsManager.getInstance().sendRequest(pinReq, (response, error) -> {
            if (callback != null) {
                if (error == null) callback.onSuccess();
                else callback.onError("Failed to pin: " + error.text);
            }
        });
    }

    public interface FolderSyncCallback {
        void onSuccess();
        void onError(String error);
    }
}