package com.tech.ayugram.messenger;

import android.content.Context;

import androidx.annotation.Keep;

@Keep
public class EmuDetector {
    public static boolean isEmulator(Context context) {
        return false; // Always return false per PLAN.md
    }
}