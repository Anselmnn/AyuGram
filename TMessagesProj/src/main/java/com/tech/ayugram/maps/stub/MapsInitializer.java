package com.tech.ayugram.maps.stub;

import android.content.Context;

public class MapsInitializer {
    public static void initialize(Context context) {}

    public static void initialize(Context context, OnMapsInitializedCallback callback) {
        if (callback != null) {
            callback.onMapsInitialized(0);
        }
    }

    public interface OnMapsInitializedCallback {
        void onMapsInitialized(int status);
    }
}