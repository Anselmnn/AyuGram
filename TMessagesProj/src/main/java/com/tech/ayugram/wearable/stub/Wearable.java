package com.tech.ayugram.wearable.stub;

import android.content.Context;
import android.net.Uri;

public class Wearable {
    private Wearable() {}

    public static class MessageApi {
        public static void sendMessage(GoogleApiClient googleApiClient, String nodeId, String path, byte[] data) {}
    }

    public static class NodeApi {
        public static void getConnectedNodes(GoogleApiClient googleApiClient, ResultCallback<NodeApi.GetConnectedNodesResult> callback) {}

        public interface GetConnectedNodesResult {
            java.util.List<Node> getNodes();
        }
    }

    public static class DataApi {
        public static void putDataItem(GoogleApiClient googleApiClient, DataItem dataItem) {}

        public static void getDataItems(GoogleApiClient googleApiClient, ResultCallback<DataItemBuffer> callback) {}
    }

    public static class Node {
        public String getId() { return ""; }
        public String getDisplayName() { return ""; }
        public boolean isNearby() { return false; }
    }

    public static class DataItem {
        public Uri getUri() { return null; }
    }

    public interface DataItemBuffer extends java.util.List<DataItem> {
        void release();
    }
}