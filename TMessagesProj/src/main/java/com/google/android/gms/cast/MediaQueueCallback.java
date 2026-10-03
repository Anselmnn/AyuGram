package com.google.android.gms.cast;

public interface MediaQueueCallback {
    void onItemsInserted(int index, int count);
    void onItemsRemoved(int index, int count);
    void onItemUpdated(int index);
    void onQueueChange();
}