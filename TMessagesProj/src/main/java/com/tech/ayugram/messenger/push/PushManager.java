package com.tech.ayugram.messenger.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;

import com.google.firebase.messaging.FirebaseMessaging;

@Keep
public class PushManager {
    private static PushManager instance;
    private final Context context;
    private final SharedPreferences prefs;
    private final UserConfig userConfig;
    
    private static final String KEY_FCM_TOKEN = "fcm_token";
    private static final String KEY_GMS_OVERRIDE_PACKAGE = "gms_override_package";
    private static final String KEY_PUSH_REGISTERED = "push_registered";

    private PushManager() {
        context = ApplicationLoader.getApplicationContext();
        prefs = context.getSharedPreferences("push_prefs", Context.MODE_PRIVATE);
        userConfig = UserConfig.getInstance();
    }

    public static synchronized PushManager getInstance() {
        if (instance == null) {
            instance = new PushManager();
        }
        return instance;
    }

    /**
     * R16: FCM only transport + GMS override for microG/ReVanced
     * Register/deregister toggle in settings
     */
    public void initialize() {
        if (userConfig.pushEnabled) {
            registerForPush();
        } else {
            unregisterFromPush();
        }
    }

    public void setPushEnabled(boolean enabled) {
        userConfig.pushEnabled = enabled;
        userConfig.saveConfig();
        
        if (enabled) {
            registerForPush();
        } else {
            unregisterFromPush();
        }
    }

    public boolean isPushEnabled() {
        return userConfig.pushEnabled;
    }

    private void registerForPush() {
        // Set GMS override if configured
        String gmsOverride = userConfig.gmsOverridePackage;
        if (gmsOverride != null && !gmsOverride.isEmpty()) {
            setGmsOverridePackage(gmsOverride);
        }
        
        // Enable FCM auto-init
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        }
        
        // Get token
        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    String token = task.getResult();
                    saveToken(token);
                    sendTokenToServer(token);
                }
            });
        
        prefs.edit().putBoolean(KEY_PUSH_REGISTERED, true).apply();
    }

    private void unregisterFromPush() {
        // Disable FCM auto-init
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            FirebaseMessaging.getInstance().setAutoInitEnabled(false);
        }
        
        // Delete token
        FirebaseMessaging.getInstance().deleteToken()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    clearToken();
                }
            });
        
        prefs.edit().putBoolean(KEY_PUSH_REGISTERED, false).apply();
    }

    public String getToken() {
        return prefs.getString(KEY_FCM_TOKEN, null);
    }

    private void saveToken(String token) {
        prefs.edit().putString(KEY_FCM_TOKEN, token).apply();
    }

    private void clearToken() {
        prefs.edit().remove(KEY_FCM_TOKEN).apply();
    }

    private void sendTokenToServer(String token) {
        // Send token to Telegram servers via MTProto
        // account.updateDeviceToken
    }

    /**
     * R16: GMS Package Override for microG/ReVanced
     * Allows using alternative GMS implementations
     */
    public void setGmsOverridePackage(String packageName) {
        userConfig.gmsOverridePackage = packageName;
        userConfig.saveConfig();
        
        // Apply override - this would typically be done via reflection
        // on GoogleApiAvailability or by setting a system property
        System.setProperty("ayugram.gms.override", packageName);
    }

    public String getGmsOverridePackage() {
        return userConfig.gmsOverridePackage;
    }

    public boolean isGmsOverrideActive() {
        String override = getGmsOverridePackage();
        return override != null && !override.isEmpty();
    }

    /**
     * Called when FCM token is refreshed
     */
    public void onTokenRefresh(String token) {
        saveToken(token);
        sendTokenToServer(token);
    }

    /**
     * Called when push message received
     */
    public void onMessageReceived(com.google.firebase.messaging.RemoteMessage remoteMessage) {
        // Handle push notification
        // Parse data and show notification via NotificationsController
    }

    /**
     * Check if push is properly registered
     */
    public boolean isRegistered() {
        return prefs.getBoolean(KEY_PUSH_REGISTERED, false) && getToken() != null;
    }
}