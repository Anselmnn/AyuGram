package com.google.android.gms.wearable;

public class MessageEvent {
    private String path;
    private byte[] data;
    private String sourceNodeId;
    private int requestId;

    public MessageEvent() {
    }

    public String getPath() {
        return path;
    }

    public byte[] getData() {
        return data;
    }

    public String getSourceNodeId() {
        return sourceNodeId;
    }

    public int getRequestId() {
        return requestId;
    }
}