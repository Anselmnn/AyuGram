package com.tech.ayugram.messenger;

import androidx.annotation.Keep;

@Keep
public class FlagSecureReason {
    public static final int FLAG_SECURE_NONE = 0;
    public static final int FLAG_SECURE_SECRET_CHAT = 1;
    public static final int FLAG_SECURE_PASSWORD = 2;
    public static final int FLAG_SECURE_SCREENSHOT = 3;
    
    // Per R10: screenshots always work in all chats
    // This class is kept for compatibility but should not block screenshots
}