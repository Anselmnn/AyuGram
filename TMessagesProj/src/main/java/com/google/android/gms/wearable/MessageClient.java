package com.google.android.gms.wearable;

import com.google.android.gms.tasks.Task;

public class MessageClient {
    private android.content.Context context;

    public MessageClient(android.content.Context context) {
        this.context = context;
    }

    public MessageClient(android.app.Activity activity) {
        this.context = activity;
    }

    public Task<Void> sendMessage(String nodeId, String path, byte[] data) {
        return null;
    }

    public Task<Void> sendMessage(String nodeId, String path) {
        return sendMessage(nodeId, path, new byte[0]);
    }

    public void addListener(OnMessageReceivedListener listener) {
    }

    public void removeListener(OnMessageReceivedListener listener) {
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(MessageEvent messageEvent);
    }
}