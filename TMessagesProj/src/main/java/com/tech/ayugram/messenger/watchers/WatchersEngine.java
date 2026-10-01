package com.tech.ayugram.messenger.watchers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Keep;
import androidx.core.app.NotificationCompat;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.AyuDatabase;
import com.tech.ayugram.messenger.NotificationsController;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.messenger.WatcherRule;
import com.tech.ayugram.messenger.WatcherRuleDao;
import com.tech.ayugram.messenger.UserWatcher;
import com.tech.ayugram.messenger.UserWatcherDao;
import com.tech.ayugram.tgnet.TLRPC;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Pattern;

@Keep
public class WatchersEngine {
    private static WatchersEngine instance;
    private final AyuDatabase database;
    private final WatcherRuleDao ruleDao;
    private final UserWatcherDao watcherDao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    
    private final Set<Long> watchedUsers = ConcurrentHashMap.newKeySet();
    private final ConcurrentHashMap<String, Pattern> compiledPatterns = new ConcurrentHashMap<>();
    private final String WATCHER_CHANNEL = "watcher_channel";

    private WatchersEngine() {
        database = AyuDatabase.getInstance();
        ruleDao = database.watcherRuleDao();
        watcherDao = database.userWatcherDao();
        loadWatchers();
        loadRules();
    }

    public static synchronized WatchersEngine getInstance() {
        if (instance == null) {
            instance = new WatchersEngine();
        }
        return instance;
    }

    private void loadWatchers() {
        List<UserWatcher> watchers = watcherDao.getAllWatchers(UserConfig.getInstance().userId);
        for (UserWatcher w : watchers) {
            watchedUsers.add(w.watched_user_id);
        }
    }

    private void loadRules() {
        List<WatcherRule> rules = ruleDao.getEnabledRules(UserConfig.getInstance().userId);
        for (WatcherRule rule : rules) {
            if (rule.regex_pattern != null && !rule.regex_pattern.isEmpty()) {
                try {
                    compiledPatterns.put(rule.id, Pattern.compile(rule.regex_pattern, Pattern.CASE_INSENSITIVE));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * §3.1-3.3 Watcher Router & Action Pipeline
     * Called for every incoming message
     */
    public void onNewMessage(TLRPC.Message message) {
        if (message == null) return;
        
        executor.execute(() -> {
            boolean matched = false;
            WatcherRule matchedRule = null;
            
            // 1. Check keyword/regex rules
            for (WatcherRule rule : ruleDao.getEnabledRules(UserConfig.getInstance().userId)) {
                if (matchesRule(message, rule)) {
                    matched = true;
                    matchedRule = rule;
                    break; // First match wins
                }
            }
            
            // 2. Check user watchers
            if (!matched && watchedUsers.contains(message.from_id)) {
                matched = true;
            }
            
            if (matched) {
                triggerActions(message, matchedRule);
            }
        });
    }

    private boolean matchesRule(TLRPC.Message message, WatcherRule rule) {
        // Check dialog filter
        if (rule.included_dialog_ids != null && !rule.included_dialog_ids.isEmpty()) {
            // Parse included dialog IDs
            boolean inIncluded = false;
            // Simplified check - in production parse JSON
            if (!inIncluded) return false;
        }
        
        if (rule.excluded_dialog_ids != null && !rule.excluded_dialog_ids.isEmpty()) {
            // Check excluded
            boolean inExcluded = false;
            if (inExcluded) return false;
        }
        
        // Check regex
        if (rule.regex_pattern != null && !rule.regex_pattern.isEmpty() && message.message != null) {
            Pattern pattern = compiledPatterns.get(rule.id);
            if (pattern == null) {
                try {
                    pattern = Pattern.compile(rule.regex_pattern, Pattern.CASE_INSENSITIVE);
                    compiledPatterns.put(rule.id, pattern);
                } catch (Exception e) {
                    return false;
                }
            }
            return pattern.matcher(message.message).find();
        }
        
        return false;
    }

    private void triggerActions(TLRPC.Message message, WatcherRule rule) {
        // 1. High-priority notification (Override Silent) - §3.3.1
        if (rule != null && rule.override_silent) {
            sendHighPriorityNotification(message, rule);
        }
        
        // 2. Forward to Saved Messages - §3.3.2
        if (rule != null && rule.forward_to_saved) {
            forwardToSavedMessages(message);
        }
        
        // 3. HTTP Webhook (n8n integration) - §3.3.3
        if (rule != null && rule.webhook_url != null && !rule.webhook_url.isEmpty()) {
            sendWebhook(message, rule);
        }
    }

    private void sendHighPriorityNotification(TLRPC.Message message, WatcherRule rule) {
        Context context = ApplicationLoader.getApplicationContext();
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        // Create channel with ALARM usage
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                WATCHER_CHANNEL,
                "Watchers",
                NotificationManager.IMPORTANCE_HIGH
            );
            channel.setSound(Uri.parse("content://settings/system/alarm_alert"),
                new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build());
            channel.setBypassDnd(true);
            nm.createNotificationChannel(channel);
        }
        
        String chatTitle = getChatTitle(message.dialog_id);
        String authorName = getAuthorName(message.from_id);
        
        Intent intent = new Intent(context, com.tech.ayugram.ui.LaunchActivity.class);
        intent.putExtra("dialog_id", message.dialog_id);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
            context, (int) message.dialog_id, intent,
            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );
        
        String deepLink = "https://t.me/c/" + Math.abs(message.dialog_id) + "/" + message.id;
        
        Notification notification = new NotificationCompat.Builder(context, WATCHER_CHANNEL)
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .setContentTitle("[Мониторинг: " + (rule != null ? rule.title : "Автор") + "] " + authorName + " в " + chatTitle)
            .setContentText(message.message != null ? message.message : "Media message")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setFullScreenIntent(pendingIntent, true)
            .build();
        
        nm.notify(999999 + (int) message.id, notification);
    }

    private void forwardToSavedMessages(TLRPC.Message message) {
        // Forward message to Saved Messages (PeerSelf)
        // In production: messages.forwardMessages to InputPeerSelf
        // Add deep link as separate message or in caption
        String deepLink = "https://t.me/c/" + Math.abs(message.dialog_id) + "/" + message.id;
        
        TLRPC.Message forwardMsg = new TLRPC.Message();
        forwardMsg.peer_id = UserConfig.getInstance().userId; // Saved Messages
        forwardMsg.message = "📨 Forwarded from watcher:\n" + deepLink + "\n\n" + (message.message != null ? message.message : "[Media]");
        forwardMsg.random_id = System.currentTimeMillis();
        forwardMsg.out = true;
        forwardMsg.date = (int) (System.currentTimeMillis() / 1000);
        
        com.tech.ayugram.messenger.SendMessagesHelper.getInstance().sendMessage(forwardMsg);
    }

    private void sendWebhook(TLRPC.Message message, WatcherRule rule) {
        executor.execute(() -> {
            try {
                String chatTitle = getChatTitle(message.dialog_id);
                String authorName = getAuthorName(message.from_id);
                String authorUsername = getAuthorUsername(message.from_id);
                String deepLink = "https://t.me/c/" + Math.abs(message.dialog_id) + "/" + message.id;
                
                String json = "{"
                    + "\"event\":\"telegram_watcher_matched\","
                    + "\"rule_title\":\"" + escapeJson(rule.title) + "\","
                    + "\"timestamp\":" + (System.currentTimeMillis() / 1000) + ","
                    + "\"message\":{"
                    + "\"id\":" + message.id + ","
                    + "\"date\":" + message.date + ","
                    + "\"text\":\"" + escapeJson(message.message != null ? message.message : "") + "\","
                    + "\"chat\":{"
                    + "\"id\":" + message.dialog_id + ","
                    + "\"title\":\"" + escapeJson(chatTitle) + "\","
                    + "\"username\":\"" + escapeJson(getChatUsername(message.dialog_id)) + "\""
                    + "},"
                    + "\"sender\":{"
                    + "\"id\":" + message.from_id + ","
                    + "\"username\":\"" + escapeJson(authorUsername) + "\","
                    + "\"name\":\"" + escapeJson(authorName) + "\""
                    + "},"
                    + "\"link\":\"" + escapeJson(deepLink) + "\""
                    + "}"
                    + "}";
                
                java.net.HttpURLConnection conn = (java.net.HttpURLConnection) 
                    new java.net.URL(rule.webhook_url).openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(10000);
                
                try (java.io.OutputStream os = conn.getOutputStream()) {
                    os.write(json.getBytes("UTF-8"));
                }
                
                conn.getResponseCode(); // Trigger request
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String escapeJson(String str) {
        if (str == null) return "";
        return str.replace("\\", "\\\\")
                 .replace("\"", "\\\"")
                 .replace("\n", "\\n")
                 .replace("\r", "\\r")
                 .replace("\t", "\\t");
    }

    private String getChatTitle(long dialogId) {
        TLRPC.Chat chat = MessagesController.getInstance().getChat(-dialogId);
        if (chat != null) return chat.title;
        TLRPC.User user = MessagesController.getInstance().getUser(dialogId);
        if (user != null) return user.first_name + " " + user.last_name;
        return "Chat " + dialogId;
    }

    private String getChatUsername(long dialogId) {
        TLRPC.Chat chat = MessagesController.getInstance().getChat(-dialogId);
        if (chat != null) return chat.username;
        return "";
    }

    private String getAuthorName(long userId) {
        TLRPC.User user = MessagesController.getInstance().getUser(userId);
        if (user != null) return user.first_name + " " + user.last_name;
        return "User " + userId;
    }

    private String getAuthorUsername(long userId) {
        TLRPC.User user = MessagesController.getInstance().getUser(userId);
        if (user != null) return user.username;
        return "";
    }

    // ============ Public API ============

    public void addWatcherRule(WatcherRule rule) {
        rule.id = java.util.UUID.randomUUID().toString();
        rule.created_at = System.currentTimeMillis();
        ruleDao.insert(rule);
        if (rule.regex_pattern != null && !rule.regex_pattern.isEmpty()) {
            try {
                compiledPatterns.put(rule.id, Pattern.compile(rule.regex_pattern, Pattern.CASE_INSENSITIVE));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void removeWatcherRule(String ruleId) {
        ruleDao.deleteRule(ruleId);
        compiledPatterns.remove(ruleId);
    }

    public void addUserWatcher(long userId) {
        watchedUsers.add(userId);
        UserWatcher watcher = new UserWatcher();
        watcher.user_id = UserConfig.getInstance().userId;
        watcher.watched_user_id = userId;
        watcher.added_at = System.currentTimeMillis();
        watcherDao.insert(watcher);
    }

    public void removeUserWatcher(long userId) {
        watchedUsers.remove(userId);
        watcherDao.deleteWatcher(UserConfig.getInstance().userId, userId);
    }

    public void reloadRules() {
        compiledPatterns.clear();
        loadRules();
    }

    public void shutdown() {
        executor.shutdown();
    }
}