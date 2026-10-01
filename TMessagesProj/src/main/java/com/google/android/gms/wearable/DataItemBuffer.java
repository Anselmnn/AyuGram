package com.google.android.gms.wearable;

public class DataItemBuffer implements java.io.Closeable, java.lang.Iterable<DataItem> {
    @Override
    public void close() {
    }

    @Override
    public java.util.Iterator<DataItem> iterator() {
        return java.util.Collections.emptyIterator();
    }

    public int getCount() {
        return 0;
    }

    public DataItem get(int position) {
        return null;
    }
}