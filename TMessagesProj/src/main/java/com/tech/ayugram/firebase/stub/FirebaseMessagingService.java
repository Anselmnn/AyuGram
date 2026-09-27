package com.tech.ayugram.firebase.stub;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class FirebaseMessagingService extends Service {
    @Override
    public IBinder onBind(Intent intent) { return null; }

    public void onMessageReceived(com.tech.ayugram.firebase.stub.RemoteMessage message) {}
    public void onNewToken(String token) {}
    public void onDeletedMessages() {}
    public void onSend(String msgId) {}
    public void onSendError(String msgId, Exception exception) {}
}