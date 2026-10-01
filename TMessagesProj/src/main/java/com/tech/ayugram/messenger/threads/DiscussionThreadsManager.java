package com.tech.ayugram.messenger.threads;

import androidx.annotation.Keep;

import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.List;

@Keep
public class DiscussionThreadsManager {
    private static DiscussionThreadsManager instance;
    
    private DiscussionThreadsManager() {
    }

    public static synchronized DiscussionThreadsManager getInstance() {
        if (instance == null) {
            instance = new DiscussionThreadsManager();
        }
        return instance;
    }

    /**
     * §4.2-4.3 Проверка isServerThread
     * Определяет, является ли тред комментариями к посту канала (server-side pagination)
     */
    public boolean isServerThread(long dialogId, TLRPC.Message rootMessage) {
        if (rootMessage == null) return false;
        
        TLRPC.Chat chat = MessagesController.getInstance().getChat(-dialogId);
        if (chat == null) return false;
        
        // Case 1: Channel broadcast
        if (chat.broadcast) {
            return true;
        }
        
        // Case 2: Supergroup with linked channel
        if (chat.megagroup && chat.linked_chat_id != 0) {
            // If message is forwarded from linked channel
            if (rootMessage.forward_from_chat_id == chat.linked_chat_id) {
                return true;
            }
            // If message has replies.isComments flag
            if (rootMessage.replies != null && rootMessage.replies.isComments) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * §4.3 Загрузка комментариев через messages.getReplies
     * При isServerThread == true всегда используем MTProto API messages.getReplies
     * с серверной пагинацией (loadBefore/loadAfter с offset_id)
     */
    public void loadReplies(long dialogId, int msgId, int offsetId, int limit, 
                           boolean loadBefore, ThreadLoadCallback callback) {
        TLRPC.TL_messages_getReplies req = new TLRPC.TL_messages_getReplies();
        req.peer = MessagesController.getInstance().getInputPeer(dialogId);
        req.msg_id = msgId;
        req.offset_id = offsetId;
        req.limit = limit;
        
        if (loadBefore) {
            // Load older messages (scroll up)
            req.add_offset = 0;
        } else {
            // Load newer messages (scroll down)
            req.add_offset = -limit;
        }
        
        ConnectionsManager.getInstance().sendRequest(req, (response, error) -> {
            if (error == null && response instanceof TLRPC.messages_Messages) {
                TLRPC.messages_Messages msgs = (TLRPC.messages_Messages) response;
                callback.onLoaded(msgs.messages, msgs.count);
            } else {
                callback.onError(error != null ? error.text : "Unknown error");
            }
        });
    }

    /**
     * Загрузка для обычных чатов (local tree) - §4.2
     * Фильтрация сообщений по reply_to_msg_id
     */
    public List<TLRPC.Message> getLocalReplies(long dialogId, int rootMsgId, int limit) {
        // In production: query local DB for messages with reply_to_msg_id == rootMsgId
        // For now return empty list - will be implemented with Room
        return new ArrayList<>();
    }

    public interface ThreadLoadCallback {
        void onLoaded(List<TLRPC.Message> messages, int totalCount);
        void onError(String error);
    }
}