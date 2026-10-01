package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;

public class FirebaseMessaging {
    private static FirebaseMessaging instance;

    private FirebaseMessaging() {
    }

    public static FirebaseMessaging getInstance() {
        if (instance == null) {
            instance = new FirebaseMessaging();
        }
        return instance;
    }

    public void subscribeToTopic(String topic) {
    }

    public void unsubscribeFromTopic(String topic) {
    }

    public com.google.android.gms.tasks.Task<String> getToken() {
        return null;
    }

    public void setAutoInitEnabled(boolean enabled) {
    }

    public boolean isAutoInitEnabled() {
        return true;
    }

    public static void setDefaultNotificationChannelId(Context context, String channelId) {
    }

    public static void setDefaultNotificationIcon(Context context, int iconRes) {
    }
}