package com.tech.ayugram.messenger.chromecast;

import android.content.Context;

import androidx.annotation.Nullable;

import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.FileLog;
import com.tech.ayugram.messenger.MediaController;
import com.tech.ayugram.messenger.MessageObject;
import com.tech.ayugram.messenger.NotificationCenter;
import com.tech.ayugram.messenger.SharedConfig;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.messenger.Utilities;
import com.tech.ayugram.tgnet.TLRPC;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Phase 2: Stub implementation of ChromecastController (Cast dependency removed)
 * All methods are no-ops.
 */
public class ChromecastController {
    private static volatile ChromecastController instance;
    private final Map<String, Object> mediaItems = new ConcurrentHashMap<>();

    public static ChromecastController getInstance() {
        if (instance == null) {
            instance = new ChromecastController();
        }
        return instance;
    }

    private ChromecastController() {
        // Stub: no Cast initialization
    }

    public boolean isCasting() {
        return false;
    }

    public void setCurrentMediaAndCastIfNeeded(ChromecastMediaVariations media) {
        // Stub: no casting
    }

    public void setCurrentMediaAndCastIfNeeded(Object media) {
        // Stub: no casting
    }

    public void onDestroy() {
        // Stub
    }

    public void stopCasting() {
        // Stub
    }

    public void setVolume(float volume) {
        // Stub
    }

    public void play() {
        // Stub
    }

    public void pause() {
        // Stub
    }

    public void seekTo(long position) {
        // Stub
    }

    public void setPlaybackRate(double rate) {
        // Stub
    }

    public void setOnCastingStateChangedListener(OnCastingStateChangedListener listener) {
        // Stub
    }

    public interface OnCastingStateChangedListener {
        void onCastingStateChanged(boolean isCasting);
    }
}