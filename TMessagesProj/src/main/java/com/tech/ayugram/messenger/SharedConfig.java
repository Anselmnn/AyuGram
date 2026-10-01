package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

@Keep
public class SharedConfig {
    public static boolean saveToGallery = true;
    public static boolean autoPlayGifs = true;
    public static boolean autoPlayVideo = true;
    public static boolean raiseToSpeak = false;
    public static boolean directShare = true;
    public static boolean useSystemFonts = false;
    public static float fontSize = 1.0f;
    public static int theme = 0; // 0 = system, 1 = light, 2 = dark
    public static boolean passcodeEnabled = false;
    public static String passcodeHash = "";
    public static long passcodeRetryTime = 0;
    public static int passcodeAttempts = 0;
    public static boolean autosaveExceptions = false;
    public static String autosaveExceptionsList = "";
    public static boolean suggestionsEnabled = true;
    public static boolean stickersEnabled = true;
    public static boolean gifEnabled = true;
    public static boolean emojiEnabled = true;
    public static boolean chatBackgroundEnabled = true;
    public static String chatBackgroundPath = "";
    public static int chatBackgroundColor = 0;
    public static boolean showLinkPreview = true;
    public static boolean sendByEnter = false;
    public static boolean hideContacts = false;
    public static boolean hidePhoneNumber = false;
    public static boolean hideLastSeen = false;
    public static boolean hideProfilePhoto = false;
    public static boolean hideForwardedFrom = false;
    public static boolean noSound = false;
    public static boolean noVibrate = false;
    public static boolean noPreview = false;
    public static boolean keepMedia = false;
    public static boolean autoDownloadWiFi = true;
    public static boolean autoDownloadMobile = false;
    public static boolean autoDownloadRoaming = false;
    public static long maxCacheSize = 1024 * 1024 * 1024; // 1GB
    public static int cacheTime = 7; // days
    public static boolean useProxy = false;
    public static String proxyHost = "";
    public static int proxyPort = 0;
    public static String proxyUser = "";
    public static String proxyPass = "";
    public static int proxyType = 0; // 0 = SOCKS5, 1 = HTTP
    
    // AyuGram specific
    public static boolean ghostMode = false;
    public static boolean hideReactions = false;
    public static boolean rememberSendOptions = false;
    public static boolean hidePanelButtons = false;
    public static boolean localPremium = false;
    public static boolean saveRestoreEnabled = false;
    public static String regexFilters = "";
    public static boolean forwarderEnabled = false;
    public static String forwarderRules = "";
    public static boolean pushEnabled = true;
    public static String gmsOverridePackage = "";
}