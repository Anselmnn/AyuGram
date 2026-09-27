package com.tech.ayugram.wearable.stub;

public class NodeApi {
    public static void getConnectedNodes(GoogleApiClient googleApiClient, ResultCallback<NodeApi.GetConnectedNodesResult> callback) {}

    public interface GetConnectedNodesResult {
        java.util.List<Node> getNodes();
    }
}