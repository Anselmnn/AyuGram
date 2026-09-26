package com.tech.ayugram.wearable.stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Phase 2: Stub for com.google.android.gms.wearable.WearableListenerService
 * Wearable dependency removed in Phase 2
 */
public class WearableListenerService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onMessageReceived(MessageEvent messageEvent) {
        // No-op
    }

    public void onDataChanged(com.tech.ayugram.wearable.stub.DataEventBuffer dataEvents) {
        // No-op
    }
}