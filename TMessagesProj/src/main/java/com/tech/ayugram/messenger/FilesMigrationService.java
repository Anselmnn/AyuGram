package com.tech.ayugram.messenger;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Keep;

@Keep
public class FilesMigrationService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Migrate files from old storage locations
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}