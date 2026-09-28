package com.tech.ayugram.play.stub;

import android.content.Context;

public class LocationServices {
    public static final String API = "LocationServices";

    public static FusedLocationProviderClient getFusedLocationProviderClient(Context context) {
        return new FusedLocationProviderClient();
    }

    public static SettingsClient getSettingsClient(Context context) {
        return new SettingsClient();
    }
}