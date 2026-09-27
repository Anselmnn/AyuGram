package com.tech.ayugram.wearable.stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class WearableListenerService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onMessageReceived(MessageEvent messageEvent) {}

    public void onDataChanged(DataEventBuffer dataEvents) {}
}