package com.tech.ayugram.messenger;

import android.content.Context;

import androidx.annotation.Keep;

@Keep
public class EmuInputDevicesDetector {
    public static boolean hasExternalInputDevices(Context context) {
        return false; // Always return false per PLAN.md
    }
}