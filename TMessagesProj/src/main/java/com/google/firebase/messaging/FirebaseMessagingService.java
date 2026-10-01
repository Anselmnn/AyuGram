package com.google.firebase.messaging;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class FirebaseMessagingService extends Service {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    @Override
    public void onNewToken(String token) {
    }

    @Override
    public void onDeletedMessages() {
    }

    @Override
    public void onMessageSent(String msgId) {
    }

    @Override
    public void onSendError(String msgId, Exception exception) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}