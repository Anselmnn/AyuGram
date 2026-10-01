package com.tech.ayugram.messenger.premium;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;

import java.util.HashSet;
import java.util.Set;

@Keep
public class LocalPremiumManager {
    private static LocalPremiumManager instance;
    private final SharedPreferences prefs;
    
    // Premium features that are unlocked locally
    private final Set<String> unlockedFeatures = new HashSet<>();
    
    private LocalPremiumManager() {
        prefs = ApplicationLoader.getPreferences();
        loadUnlockedFeatures();
    }

    public static synchronized LocalPremiumManager getInstance() {
        if (instance == null) {
            instance = new LocalPremiumManager();
        }
        return instance;
    }

    private void loadUnlockedFeatures() {
        if (!UserConfig.getInstance().localPremium) return;
        
        String features = prefs.getString("local_premium_features", "");
        if (!features.isEmpty()) {
            String[] parts = features.split(",");
            for (String part : parts) {
                unlockedFeatures.add(part.trim());
            }
        } else {
            // Default unlocked features
            unlockAllDefaultFeatures();
        }
    }

    private void unlockAllDefaultFeatures() {
        unlockedFeatures.add("double_upload_limit");      // 4GB instead of 2GB
        unlockedFeatures.add("more_folders");             // 20 folders instead of 10
        unlockedFeatures.add("more_pinned_chats");        // 10 pinned instead of 5
        unlockedFeatures.add("more_saved_messages_tags"); // More tags in Saved Messages
        unlockedFeatures.add("custom_emoji");             // Custom emoji in status/bio
        unlockedFeatures.add("animated_profile");         // Animated profile picture
        unlockedFeatures.add("premium_stickers");         // Access to premium stickers
        unlockedFeatures.add("advanced_chat_management"); // Chat folders, etc.
        unlockedFeatures.add("voice_to_text");            // Voice message transcription
        unlockedFeatures.add("no_ads");                   // No sponsored messages
        unlockedFeatures.add("premium_reactions");        // More reactions
        unlockedFeatures.add("premium_app_icons");        // Custom app icons
        unlockedFeatures.add("translation");              // Message translation
        unlockedFeatures.add("download_manager");         // Advanced download manager
        
        saveUnlockedFeatures();
    }

    public boolean isFeatureUnlocked(String feature) {
        return UserConfig.getInstance().localPremium && unlockedFeatures.contains(feature);
    }

    public void setLocalPremium(boolean enabled) {
        UserConfig config = UserConfig.getInstance();
        config.localPremium = enabled;
        if (enabled) {
            unlockAllDefaultFeatures();
        } else {
            unlockedFeatures.clear();
        }
        config.saveConfig();
    }

    public boolean isLocalPremiumActive() {
        return UserConfig.getInstance().localPremium;
    }

    public long getPremiumExpires() {
        return UserConfig.getInstance().premiumExpires;
    }

    public void setPremiumExpires(long expires) {
        UserConfig config = UserConfig.getInstance();
        config.premiumExpires = expires;
        config.saveConfig();
    }

    public void unlockFeature(String feature) {
        unlockedFeatures.add(feature);
        saveUnlockedFeatures();
    }

    public void lockFeature(String feature) {
        unlockedFeatures.remove(feature);
        saveUnlockedFeatures();
    }

    private void saveUnlockedFeatures() {
        StringBuilder sb = new StringBuilder();
        for (String feature : unlockedFeatures) {
            if (sb.length() > 0) sb.append(",");
            sb.append(feature);
        }
        prefs.edit().putString("local_premium_features", sb.toString()).apply();
    }

    // Premium limits
    public int getMaxUploadSize() {
        return isFeatureUnlocked("double_upload_limit") ? 4 * 1024 * 1024 * 1024 : 2 * 1024 * 1024 * 1024; // 4GB vs 2GB
    }

    public int getMaxFolders() {
        return isFeatureUnlocked("more_folders") ? 20 : 10;
    }

    public int getMaxPinnedChats() {
        return isFeatureUnlocked("more_pinned_chats") ? 10 : 5;
    }

    public int getMaxSavedTags() {
        return isFeatureUnlocked("more_saved_messages_tags") ? 20 : 5;
    }
}