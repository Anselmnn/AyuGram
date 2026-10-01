package com.google.android.gms.wearable;

import com.google.android.gms.tasks.Task;

public class DataClient {
    private android.content.Context context;

    public DataClient(android.content.Context context) {
        this.context = context;
    }

    public DataClient(android.app.Activity activity) {
        this.context = activity;
    }

    public Task<Void> putDataItem(PutDataMapRequest request) {
        return null;
    }

    public Task<DataItemBuffer> getDataItems() {
        return null;
    }

    public Task<DataItemBuffer> getDataItems(DataItemAsset dataItemAsset) {
        return null;
    }

    public void addListener(OnDataChangedListener listener) {
    }

    public void removeListener(OnDataChangedListener listener) {
    }

    public interface OnDataChangedListener {
        void onDataChanged(DataEventBuffer dataEventBuffer);
    }
}