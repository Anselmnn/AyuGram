package com.tech.ayugram.messenger;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.ui.LaunchActivity;

@Keep
public class NotificationsController {
    private static NotificationsController[] instances = new NotificationsController[3];
    
    private static final String CHANNEL_DEFAULT = "default_channel";
    private static final String CHANNEL_MESSAGES = "messages_channel";
    private static final String CHANNEL_CALLS = "calls_channel";
    private static final String CHANNEL_WATCHER = "watcher_channel"; // §3 Watchers
    
    private NotificationManager notificationManager;
    private Context context;

    private NotificationsController(int accountNum) {
        context = ApplicationLoader.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        createChannels();
    }

    public static NotificationsController getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new NotificationsController(num);
        }
        return instances[num];
    }

    public static NotificationsController getInstance() {
        return getInstance(0);
    }

    private void createChannels() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        
        // Default channel
        NotificationChannel defaultChannel = new NotificationChannel(
            CHANNEL_DEFAULT, "Default", NotificationManager.IMPORTANCE_DEFAULT);
        defaultChannel.setDescription("Default notifications");
        notificationManager.createNotificationChannel(defaultChannel);
        
        // Messages channel
        NotificationChannel messagesChannel = new NotificationChannel(
            CHANNEL_MESSAGES, "Messages", NotificationManager.IMPORTANCE_HIGH);
        messagesChannel.setDescription("New messages");
        messagesChannel.setSound(Uri.parse("content://settings/system/notification_sound"), 
            new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION).build());
        notificationManager.createNotificationChannel(messagesChannel);
        
        // Calls channel
        NotificationChannel callsChannel = new NotificationChannel(
            CHANNEL_CALLS, "Calls", NotificationManager.IMPORTANCE_HIGH);
        callsChannel.setDescription("Incoming calls");
        callsChannel.setSound(Uri.parse("content://settings/system/ringtone"), 
            new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE).build());
        notificationManager.createNotificationChannel(callsChannel);
        
        // Watcher channel (high priority - §3)
        NotificationChannel watcherChannel = new NotificationChannel(
            CHANNEL_WATCHER, "Watchers", NotificationManager.IMPORTANCE_HIGH);
        watcherChannel.setDescription("Watcher notifications (high priority)");
        watcherChannel.setSound(Uri.parse("content://settings/system/alarm_alert"), 
            new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build());
        watcherChannel.setBypassDnd(true);
        notificationManager.createNotificationChannel(watcherChannel);
    }

    public void showMessageNotification(String title, String text, long dialogId, int msgId) {
        Intent intent = new Intent(context, LaunchActivity.class);
        intent.putExtra("dialog_id", dialogId);
        intent.putExtra("message_id", msgId);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
            context, (int) dialogId, intent, 
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_MESSAGES)
            .setSmallIcon(android.R.drawable.ic_dialog_email)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build();
        
        notificationManager.notify((int) dialogId, notification);
    }

    public void showWatcherNotification(String ruleTitle, String author, String chatTitle, String messageText, String deepLink) {
        Intent intent = new Intent(context, LaunchActivity.class);
        if (deepLink != null) {
            intent.setData(Uri.parse(deepLink));
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
            context, 0, intent, 
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        
        Notification notification = new NotificationCompat.Builder(context, CHANNEL_WATCHER)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("[Мониторинг: " + ruleTitle + "] " + author + " в " + chatTitle)
            .setContentText(messageText)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true)
            .build();
        
        notificationManager.notify(999999, notification);
    }

    public void cancelNotification(int id) {
        notificationManager.cancel(id);
    }

    public void cancelAll() {
        notificationManager.cancelAll();
    }
}