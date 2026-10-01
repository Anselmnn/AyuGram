package com.tech.ayugram.messenger;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Keep;

@Keep
public class NotificationsService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Foreground service for notifications
            Notification notification = new Notification.Builder(this, "default_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("AyuGram")
                .setContentText("Running")
                .build();
            startForeground(1, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}