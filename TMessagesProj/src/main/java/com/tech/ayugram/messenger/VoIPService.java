package com.tech.ayugram.messenger;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Keep;

@Keep
public class VoIPService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this, "calls_channel")
                .setSmallIcon(android.R.drawable.ic_menu_call)
                .setContentTitle("AyuGram Call")
                .setContentText("Active call")
                .build();
            startForeground(5, notification);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Handle VoIP call
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