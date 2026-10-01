package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class MessagesStorage {
    private static MessagesStorage[] instances = new MessagesStorage[3];
    
    private final ConcurrentHashMap<Long, TLRPC.Message> messages = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, TLRPC.Dialog> dialogs = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, TLRPC.User> users = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<Long, TLRPC.Chat> chats = new ConcurrentHashMap<>();

    private MessagesStorage(int accountNum) {
    }

    public static MessagesStorage getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new MessagesStorage(num);
        }
        return instances[num];
    }

    public static MessagesStorage getInstance() {
        return getInstance(0);
    }

    public void putMessage(TLRPC.Message message) {
        if (message != null) {
            messages.put(message.id, message);
        }
    }

    public TLRPC.Message getMessage(long msgId) {
        return messages.get(msgId);
    }

    public void putDialog(TLRPC.Dialog dialog) {
        if (dialog != null) {
            dialogs.put(dialog.id, dialog);
        }
    }

    public TLRPC.Dialog getDialog(long dialogId) {
        return dialogs.get(dialogId);
    }

    public void putUser(TLRPC.User user) {
        if (user != null) {
            users.put(user.id, user);
        }
    }

    public TLRPC.User getUser(long userId) {
        return users.get(userId);
    }

    public void putChat(TLRPC.Chat chat) {
        if (chat != null) {
            chats.put(chat.id, chat);
        }
    }

    public TLRPC.Chat getChat(long chatId) {
        return chats.get(chatId);
    }

    public List<TLRPC.Message> getMessages(long dialogId, int limit) {
        List<TLRPC.Message> result = new ArrayList<>();
        for (TLRPC.Message msg : messages.values()) {
            if (msg.dialog_id == dialogId) {
                result.add(msg);
                if (result.size() >= limit) break;
            }
        }
        return result;
    }

    public void cleanup() {
        messages.clear();
        dialogs.clear();
        users.clear();
        chats.clear();
    }
}