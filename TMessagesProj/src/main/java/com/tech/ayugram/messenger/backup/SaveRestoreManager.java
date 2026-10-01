package com.tech.ayugram.messenger.backup;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.FolderSyncManager;
import com.tech.ayugram.messenger.UserConfig;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Keep
public class SaveRestoreManager {
    private static SaveRestoreManager instance;
    private final SharedPreferences prefs;
    private final UserConfig userConfig;

    private static final String KEY_SAVE_RESTORE_ENABLED = "save_restore_enabled";
    private static final String KEY_LAST_BACKUP = "last_backup_time";
    private static final String KEY_BACKUP_VERSION = "backup_version";

    // Keys to include in backup
    private static final Set<String> BACKUP_KEYS = new HashSet<String>() {{
        // UserConfig keys (per account)
        add("ghost_mode");
        add("hide_reactions");
        add("remember_send_options");
        add("hide_panel_buttons");
        add("local_premium");
        add("premium_expires");
        add("save_restore");
        add("regex_filters");
        add("forwarder_enabled");
        add("forwarder_rules");
        add("push_enabled");
        add("gms_override");
        
        // SharedConfig keys
        add("saveToGallery");
        add("autoPlayGifs");
        add("autoPlayVideo");
        add("raiseToSpeak");
        add("directShare");
        add("useSystemFonts");
        add("fontSize");
        add("theme");
        add("passcodeEnabled");
        add("passcodeHash");
        add("passcodeRetryTime");
        add("passcodeAttempts");
        add("autosaveExceptions");
        add("autosaveExceptionsList");
        add("suggestionsEnabled");
        add("stickersEnabled");
        add("gifEnabled");
        add("emojiEnabled");
        add("chatBackgroundEnabled");
        add("chatBackgroundPath");
        add("chatBackgroundColor");
        add("showLinkPreview");
        add("sendByEnter");
        add("hideContacts");
        add("hidePhoneNumber");
        add("hideLastSeen");
        add("hideProfilePhoto");
        add("hideForwardedFrom");
        add("noSound");
        add("noVibrate");
        add("noPreview");
        add("keepMedia");
        add("autoDownloadWiFi");
        add("autoDownloadMobile");
        add("autoDownloadRoaming");
        add("maxCacheSize");
        add("cacheTime");
        add("useProxy");
        add("proxyHost");
        add("proxyPort");
        add("proxyUser");
        add("proxyPass");
        add("proxyType");
        add("ghostMode");
        add("hideReactions");
        add("rememberSendOptions");
        add("hidePanelButtons");
        add("localPremium");
        add("saveRestoreEnabled");
        add("regexFilters");
        add("forwarderEnabled");
        add("forwarderRules");
        add("pushEnabled");
        add("gmsOverridePackage");
    }};

    private SaveRestoreManager() {
        prefs = ApplicationLoader.getPreferences();
        userConfig = UserConfig.getInstance();
    }

    public static synchronized SaveRestoreManager getInstance() {
        if (instance == null) {
            instance = new SaveRestoreManager();
        }
        return instance;
    }

    public boolean isEnabled() {
        return userConfig.saveRestoreEnabled;
    }

    public void setEnabled(boolean enabled) {
        userConfig.saveRestoreEnabled = enabled;
        userConfig.saveConfig();
    }

    public long getLastBackupTime() {
        return prefs.getLong(KEY_LAST_BACKUP, 0);
    }

    /**
     * Create backup JSON from all settings
     */
    public JSONObject createBackup() {
        JSONObject backup = new JSONObject();
        try {
            backup.put("version", 1);
            backup.put("app", "AyuGram");
            backup.put("timestamp", System.currentTimeMillis() / 1000);
            backup.put("account", userConfig.currentAccount);
            
            // UserConfig settings
            JSONObject userConfigJson = new JSONObject();
            Map<String, ?> allPrefs = prefs.getAll();
            for (Map.Entry<String, ?> entry : allPrefs.entrySet()) {
                String key = entry.getKey();
                String prefix = "account" + userConfig.currentAccount + "_";
                if (key.startsWith(prefix)) {
                    String shortKey = key.substring(prefix.length());
                    if (BACKUP_KEYS.contains(shortKey)) {
                        putValue(userConfigJson, shortKey, entry.getValue());
                    }
                }
            }
            backup.put("user_config", userConfigJson);
            
            // SharedConfig settings (static fields via reflection would be better)
            // For now, include key ones manually
            JSONObject sharedConfigJson = new JSONObject();
            backup.put("shared_config", sharedConfigJson);
            
            // Regex filters
            if (userConfig.regexFilters != null && !userConfig.regexFilters.isEmpty()) {
                backup.put("regex_filters", userConfig.regexFilters);
            }
            
            // Forwarder rules
            if (userConfig.forwarderRules != null && !userConfig.forwarderRules.isEmpty()) {
                backup.put("forwarder_rules", userConfig.forwarderRules);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return backup;
    }

    /**
     * Restore settings from backup JSON
     */
    public void restoreBackup(JSONObject backup) {
        try {
            int version = backup.getInt("version");
            if (version > 1) {
                // Future version - maybe incompatible
            }
            
            JSONObject userConfigJson = backup.getJSONObject("user_config");
            SharedPreferences.Editor editor = prefs.edit();
            
            for (String key : BACKUP_KEYS) {
                if (userConfigJson.has(key)) {
                    String fullKey = "account" + userConfig.currentAccount + "_" + key;
                    Object value = userConfigJson.get(key);
                    putValueToPrefs(editor, fullKey, value);
                }
            }
            
            // Restore regex filters
            if (backup.has("regex_filters")) {
                userConfig.regexFilters = backup.getString("regex_filters");
            }
            
            // Restore forwarder rules
            if (backup.has("forwarder_rules")) {
                userConfig.forwarderRules = backup.getString("forwarder_rules");
            }
            
            editor.apply();
            userConfig.loadConfig(); // Reload UserConfig
            
            prefs.edit().putLong(KEY_LAST_BACKUP, System.currentTimeMillis()).apply();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Backup to service channel via FolderSyncManager
     */
    public void backupToCloud(FolderSyncManager.FolderSyncCallback callback) {
        if (!isEnabled()) {
            if (callback != null) callback.onError("Save/Restore not enabled");
            return;
        }
        
        FolderSyncManager.getInstance().saveConfig(new FolderSyncManager.FolderSyncCallback() {
            @Override
            public void onSuccess() {
                if (callback != null) callback.onSuccess();
            }
            @Override
            public void onError(String error) {
                if (callback != null) callback.onError(error);
            }
        });
    }

    /**
     * Restore from service channel
     */
    public void restoreFromCloud(FolderSyncManager.FolderSyncCallback callback) {
        FolderSyncManager.getInstance().initialize(new FolderSyncManager.FolderSyncCallback() {
            @Override
            public void onSuccess() {
                // Config loaded into FolderConfig entities
                // Would need to apply to UserConfig/SharedConfig
                if (callback != null) callback.onSuccess();
            }
            @Override
            public void onError(String error) {
                if (callback != null) callback.onError(error);
            }
        });
    }

    private void putValue(JSONObject json, String key, Object value) throws Exception {
        if (value instanceof String) json.put(key, value);
        else if (value instanceof Integer) json.put(key, value);
        else if (value instanceof Long) json.put(key, value);
        else if (value instanceof Boolean) json.put(key, value);
        else if (value instanceof Float) json.put(key, value);
        else json.put(key, value.toString());
    }

    private void putValueToPrefs(SharedPreferences.Editor editor, String key, Object value) {
        if (value instanceof String) editor.putString(key, (String) value);
        else if (value instanceof Integer) editor.putInt(key, (Integer) value);
        else if (value instanceof Long) editor.putLong(key, (Long) value);
        else if (value instanceof Boolean) editor.putBoolean(key, (Boolean) value);
        else if (value instanceof Float) editor.putFloat(key, (Float) value);
    }
}