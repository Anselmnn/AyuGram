package com.tech.ayugram.messenger;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Keep;

@Keep
public class BringAppForegroundService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this, "default_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("AyuGram")
                .setContentText("Bringing to foreground")
                .build();
            startForeground(2, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Bring app to foreground logic
        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}