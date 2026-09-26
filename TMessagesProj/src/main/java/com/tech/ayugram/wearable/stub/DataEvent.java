package com.tech.ayugram.wearable.stub;

import android.net.Uri;

/**
 * Phase 2: Stub for com.google.android.gms.wearable.DataEvent
 * Wearable dependency removed in Phase 2
 */
public class DataEvent {
    public static final int TYPE_CHANGED = 1;
    public static final int TYPE_DELETED = 2;
    public static final int TYPE_CHANGED = 1;

    private int type;
    private DataItem dataItem;

    public int getType() { return type; }
    public DataItem getDataItem() { return dataItem; }
    public Uri getDataItemUri() { return null; }
}