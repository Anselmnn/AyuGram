package com.tech.ayugram.messenger;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Keep;

import com.tech.ayugram.tgnet.ConnectionsManager;

@Keep
public class TelegramConnectionService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notification notification = new Notification.Builder(this, "default_channel")
                .setSmallIcon(android.R.drawable.ic_dialog_dialer)
                .setContentTitle("AyuGram")
                .setContentText("Connected")
                .build();
            startForeground(4, notification);
        }
        
        // Initialize MTProto connection
        ConnectionsManager.getInstance().setConnected(true);
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
        ConnectionsManager.getInstance().setConnected(false);
        super.onDestroy();
    }
}