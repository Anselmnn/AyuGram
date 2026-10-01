package com.tech.ayugram.play.stub;

import android.graphics.Bitmap;

public final class BitmapDescriptorFactory {
    private BitmapDescriptorFactory() {}

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        return BitmapDescriptor.fromBitmap(bitmap);
    }

    public static BitmapDescriptor fromResource(int resourceId) {
        return BitmapDescriptor.fromResource(resourceId);
    }

    public static BitmapDescriptor fromView(android.view.View view) {
        return BitmapDescriptor.fromView(view);
    }

    public static BitmapDescriptor fromPath(String assetPath) {
        return BitmapDescriptor.fromPath(assetPath);
    }

    public static BitmapDescriptor defaultMarker() {
        return BitmapDescriptor.defaultMarker();
    }

    public static BitmapDescriptor defaultMarker(float hue) {
        return BitmapDescriptor.defaultMarker(hue);
    }

    public static float HUE_RED = 0.0f;
    public static float HUE_ORANGE = 30.0f;
    public static float HUE_YELLOW = 60.0f;
    public static float HUE_GREEN = 120.0f;
    public static float HUE_CYAN = 180.0f;
    public static float HUE_AZURE = 210.0f;
    public static float HUE_BLUE = 240.0f;
    public static float HUE_VIOLET = 270.0f;
    public static float HUE_MAGENTA = 300.0f;
    public static float HUE_ROSE = 330.0f;
}