package com.tech.ayugram.messenger;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.tgnet.ConnectionsManager;
import com.tech.ayugram.tgnet.RequestDelegate;
import com.tech.ayugram.tgnet.TLObject;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

@Keep
public class SendMessagesHelper {
    private static SendMessagesHelper[] instances = new SendMessagesHelper[3];
    
    private final ConcurrentHashMap<String, TLRPC.Message> sendingMessages = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, TLRPC.Message> pendingMessages = new ConcurrentHashMap<>();

    private SendMessagesHelper(int accountNum) {
    }

    public static SendMessagesHelper getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new SendMessagesHelper(num);
        }
        return instances[num];
    }

    public static SendMessagesHelper getInstance() {
        return getInstance(0);
    }

    public void sendMessage(TLRPC.Message message) {
        if (message == null) return;
        
        String key = message.random_id + "";
        sendingMessages.put(key, message);
        
        // Send via MTProto
        ConnectionsManager.getInstance(UserConfig.getInstance().currentAccount).sendRequest(
            message,
            (response, error) -> {
                sendingMessages.remove(key);
                if (error != null) {
                    // Handle error
                    pendingMessages.put(key, message);
                }
            }
        );
    }

    public void sendMedia(TLRPC.Message message, Uri mediaUri) {
        // Upload media first, then send message
        sendMessage(message);
    }

    public void resendMessage(TLRPC.Message message) {
        String key = message.random_id + "";
        pendingMessages.remove(key);
        sendMessage(message);
    }

    public void cancelSending(TLRPC.Message message) {
        String key = message.random_id + "";
        sendingMessages.remove(key);
        pendingMessages.remove(key);
    }

    public boolean isSending(TLRPC.Message message) {
        return sendingMessages.containsKey(message.random_id + "");
    }

    public void processPendingMessages() {
        for (TLRPC.Message msg : pendingMessages.values()) {
            sendMessage(msg);
        }
        pendingMessages.clear();
    }

    public void cleanup() {
        sendingMessages.clear();
        pendingMessages.clear();
    }
}