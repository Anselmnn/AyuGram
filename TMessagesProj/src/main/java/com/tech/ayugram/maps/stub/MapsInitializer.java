package com.tech.ayugram.maps.stub;

import android.content.Context;

/**
 * Phase 2: Stub for com.google.android.gms.maps.MapsInitializer
 * Google Maps dependency removed in Phase 2
 */
public class MapsInitializer {
    public static void initialize(Context context) {
        // No-op
    }

    public static void initialize(Context context, OnMapsInitializedCallback callback) {
        if (callback != null) {
            callback.onMapsInitialized(0);
        }
    }

    public interface OnMapsInitializedCallback {
        void onMapsInitialized(int status);
    }
}