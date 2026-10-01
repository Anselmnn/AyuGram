package com.google.android.gms.wearable;

public class DataEventBuffer implements java.io.Closeable, java.lang.Iterable<DataEvent> {
    @Override
    public void close() {
    }

    @Override
    public java.util.Iterator<DataEvent> iterator() {
        return java.util.Collections.emptyIterator();
    }

    public int getCount() {
        return 0;
    }

    public DataEvent get(int position) {
        return null;
    }
}