package com.google.android.gms.wearable;

import android.net.Uri;

public class DataItem {
    private Uri uri;
    private byte[] data;

    public DataItem() {
    }

    public Uri getUri() {
        return uri;
    }

    public byte[] getData() {
        return data;
    }

    public DataItem setUri(Uri uri) {
        this.uri = uri;
        return this;
    }

    public DataItem setData(byte[] data) {
        this.data = data;
        return this;
    }
}