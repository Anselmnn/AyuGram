package com.tech.ayugram.messenger;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import androidx.annotation.Keep;

import com.tech.ayugram.ApplicationLoader;

@Keep
public class UserConfig {
    private static UserConfig[] instances = new UserConfig[3];
    
    public int currentAccount = 0;
    public long userId = 0;
    public String firstName = "";
    public String lastName = "";
    public String userName = "";
    public String phoneNumber = "";
    public String phoneHash = "";
    public boolean registered = false;
    public boolean syncedContacts = false;
    public int appVersion = 1;
    public int deviceModel = Build.VERSION.SDK_INT;
    public String systemLangCode = "en";
    public String langCode = "en";
    public String langPack = "";
    public int timeZoneOffset = 0;
    public boolean isFirstRun = true;
    
    // Local Premium (R15)
    public boolean localPremium = false;
    public long premiumExpires = 0;
    
    // Ghost Mode (R15 B1/B5/B6)
    public boolean ghostMode = false;
    public boolean hideReactions = false;
    public boolean rememberSendOptions = false;
    public boolean hidePanelButtons = false;
    
    // AyuGram Settings
    public boolean saveRestoreEnabled = false;
    public String regexFilters = "";
    public boolean forwarderEnabled = false;
    public String forwarderRules = "";
    
    // Push (R16)
    public boolean pushEnabled = true;
    public String gmsOverridePackage = "";

    private SharedPreferences preferences;
    private int accountNum;

    private UserConfig(int accountNum) {
        this.accountNum = accountNum;
        this.preferences = ApplicationLoader.getPreferences();
    }

    public static UserConfig getInstance(int num) {
        if (instances[num] == null) {
            instances[num] = new UserConfig(num);
        }
        return instances[num];
    }

    public static UserConfig getInstance() {
        return getInstance(0);
    }

    public void loadConfig() {
        String prefix = "account" + accountNum + "_";
        userId = preferences.getLong(prefix + "user_id", 0);
        firstName = preferences.getString(prefix + "first_name", "");
        lastName = preferences.getString(prefix + "last_name", "");
        userName = preferences.getString(prefix + "user_name", "");
        phoneNumber = preferences.getString(prefix + "phone_number", "");
        phoneHash = preferences.getString(prefix + "phone_hash", "");
        registered = preferences.getBoolean(prefix + "registered", false);
        syncedContacts = preferences.getBoolean(prefix + "synced_contacts", false);
        appVersion = preferences.getInt(prefix + "app_version", 1);
        systemLangCode = preferences.getString(prefix + "system_lang_code", "en");
        langCode = preferences.getString(prefix + "lang_code", "en");
        langPack = preferences.getString(prefix + "lang_pack", "");
        timeZoneOffset = preferences.getInt(prefix + "time_zone_offset", 0);
        isFirstRun = preferences.getBoolean(prefix + "is_first_run", true);
        
        // Local Premium
        localPremium = preferences.getBoolean(prefix + "local_premium", false);
        premiumExpires = preferences.getLong(prefix + "premium_expires", 0);
        
        // Ghost Mode
        ghostMode = preferences.getBoolean(prefix + "ghost_mode", false);
        hideReactions = preferences.getBoolean(prefix + "hide_reactions", false);
        rememberSendOptions = preferences.getBoolean(prefix + "remember_send_options", false);
        hidePanelButtons = preferences.getBoolean(prefix + "hide_panel_buttons", false);
        
        // AyuGram Settings
        saveRestoreEnabled = preferences.getBoolean(prefix + "save_restore", false);
        regexFilters = preferences.getString(prefix + "regex_filters", "");
        forwarderEnabled = preferences.getBoolean(prefix + "forwarder_enabled", false);
        forwarderRules = preferences.getString(prefix + "forwarder_rules", "");
        
        // Push
        pushEnabled = preferences.getBoolean(prefix + "push_enabled", true);
        gmsOverridePackage = preferences.getString(prefix + "gms_override", "");
    }

    public void saveConfig() {
        String prefix = "account" + accountNum + "_";
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(prefix + "user_id", userId);
        editor.putString(prefix + "first_name", firstName);
        editor.putString(prefix + "last_name", lastName);
        editor.putString(prefix + "user_name", userName);
        editor.putString(prefix + "phone_number", phoneNumber);
        editor.putString(prefix + "phone_hash", phoneHash);
        editor.putBoolean(prefix + "registered", registered);
        editor.putBoolean(prefix + "synced_contacts", syncedContacts);
        editor.putInt(prefix + "app_version", appVersion);
        editor.putString(prefix + "system_lang_code", systemLangCode);
        editor.putString(prefix + "lang_code", langCode);
        editor.putString(prefix + "lang_pack", langPack);
        editor.putInt(prefix + "time_zone_offset", timeZoneOffset);
        editor.putBoolean(prefix + "is_first_run", isFirstRun);
        
        // Local Premium
        editor.putBoolean(prefix + "local_premium", localPremium);
        editor.putLong(prefix + "premium_expires", premiumExpires);
        
        // Ghost Mode
        editor.putBoolean(prefix + "ghost_mode", ghostMode);
        editor.putBoolean(prefix + "hide_reactions", hideReactions);
        editor.putBoolean(prefix + "remember_send_options", rememberSendOptions);
        editor.putBoolean(prefix + "hide_panel_buttons", hidePanelButtons);
        
        // AyuGram Settings
        editor.putBoolean(prefix + "save_restore", saveRestoreEnabled);
        editor.putString(prefix + "regex_filters", regexFilters);
        editor.putBoolean(prefix + "forwarder_enabled", forwarderEnabled);
        editor.putString(prefix + "forwarder_rules", forwarderRules);
        
        // Push
        editor.putBoolean(prefix + "push_enabled", pushEnabled);
        editor.putString(prefix + "gms_override", gmsOverridePackage);
        
        editor.apply();
    }

    public void clearConfig() {
        String prefix = "account" + accountNum + "_";
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(prefix + "user_id");
        editor.remove(prefix + "first_name");
        editor.remove(prefix + "last_name");
        editor.remove(prefix + "user_name");
        editor.remove(prefix + "phone_number");
        editor.remove(prefix + "phone_hash");
        editor.remove(prefix + "registered");
        editor.remove(prefix + "synced_contacts");
        editor.remove(prefix + "app_version");
        editor.remove(prefix + "system_lang_code");
        editor.remove(prefix + "lang_code");
        editor.remove(prefix + "lang_pack");
        editor.remove(prefix + "time_zone_offset");
        editor.remove(prefix + "is_first_run");
        editor.remove(prefix + "local_premium");
        editor.remove(prefix + "premium_expires");
        editor.remove(prefix + "ghost_mode");
        editor.remove(prefix + "hide_reactions");
        editor.remove(prefix + "remember_send_options");
        editor.remove(prefix + "hide_panel_buttons");
        editor.remove(prefix + "save_restore");
        editor.remove(prefix + "regex_filters");
        editor.remove(prefix + "forwarder_enabled");
        editor.remove(prefix + "forwarder_rules");
        editor.remove(prefix + "push_enabled");
        editor.remove(prefix + "gms_override");
        editor.apply();
        
        // Reset instance
        userId = 0;
        firstName = "";
        lastName = "";
        userName = "";
        phoneNumber = "";
        phoneHash = "";
        registered = false;
        syncedContacts = false;
        localPremium = false;
        premiumExpires = 0;
        ghostMode = false;
        hideReactions = false;
        rememberSendOptions = false;
        hidePanelButtons = false;
        saveRestoreEnabled = false;
        regexFilters = "";
        forwarderEnabled = false;
        forwarderRules = "";
        pushEnabled = true;
        gmsOverridePackage = "";
    }
}