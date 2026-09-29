package com.tech.ayugram.wear.stub;

import android.content.Context;
import com.tech.ayugram.play.stub.Task;

public class Wearable {
    private Wearable() {}

    public static Task<Void> addListener(ListenerService listener) {
        return Task.forResult(null);
    }

    public static Task<Void> removeListener(ListenerService listener) {
        return Task.forResult(null);
    }

    public static MessageClient getMessageClient(Context context) {
        return new MessageClient();
    }

    public static DataClient getDataClient(Context context) {
        return new DataClient();
    }

    public static class ListenerService {
        public void onMessageReceived(MessageEvent messageEvent) {}
        public void onDataChanged(DataEventBuffer dataEventBuffer) {}
    }

    public static class MessageClient {
        public Task<Integer> sendMessage(String nodeId, String path, byte[] data) {
            return Task.forResult(0);
        }
    }

    public static class DataClient {
        // Data client methods
    }

    public static class MessageEvent {
        private String path;
        private byte[] data;

        public String getPath() { return path; }
        public byte[] getData() { return data; }
    }

    public static class DataEventBuffer {
        public int size() { return 0; }
        public DataEvent get(int index) { return null; }
    }

    public static class DataEvent {
        public static final int TYPE_CHANGED = 1;
        public static final int TYPE_DELETED = 2;
        public static final int TYPE_INVALID = 3;

        private int type;
        private DataItem dataItem;

        public int getType() { return type; }
        public DataItem getDataItem() { return dataItem; }
    }

    public static class DataItem {
        private String uri;
        private byte[] data;

        public String getUri() { return uri; }
        public byte[] getData() { return data; }
    }
}