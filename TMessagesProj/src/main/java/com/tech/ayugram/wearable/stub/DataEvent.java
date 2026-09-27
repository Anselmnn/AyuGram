package com.tech.ayugram.wearable.stub;

import android.net.Uri;

public class DataEvent {
    public static final int TYPE_CHANGED = 1;
    public static final int TYPE_DELETED = 2;

    private int type;
    private DataItem dataItem;

    public int getType() { return type; }
    public DataItem getDataItem() { return dataItem; }
    public Uri getDataItemUri() { return null; }
}