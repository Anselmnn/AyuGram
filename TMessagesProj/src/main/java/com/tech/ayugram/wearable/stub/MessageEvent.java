package com.tech.ayugram.wearable.stub;

/**
 * Phase 2: Stub for com.google.android.gms.wearable.MessageEvent
 * Wearable dependency removed in Phase 2
 */
public class MessageEvent {
    private String path;
    private byte[] data;

    public MessageEvent() {}

    public String getPath() { return path; }
    public byte[] getData() { return data; }
    public void setPath(String path) { this.path = path; }
    public void setData(byte[] data) { this.data = data; }
}