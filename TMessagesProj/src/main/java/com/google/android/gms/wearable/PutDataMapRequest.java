package com.google.android.gms.wearable;

import android.net.Uri;

public class PutDataMapRequest {
    private String path;
    private DataMap dataMap;
    private boolean urgent;

    private PutDataMapRequest(String path) {
        this.path = path;
        this.dataMap = new DataMap();
    }

    public static PutDataMapRequest create(String path) {
        return new PutDataMapRequest(path);
    }

    public static PutDataMapRequest createWithAutoAppendId(String pathPrefix) {
        return new PutDataMapRequest(pathPrefix);
    }

    public DataMap getDataMap() {
        return dataMap;
    }

    public void setUrgent() {
        this.urgent = true;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public DataItem asDataItem() {
        return new DataItem();
    }
}