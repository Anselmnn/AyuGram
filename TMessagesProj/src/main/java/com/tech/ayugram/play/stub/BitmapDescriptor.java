package com.tech.ayugram.play.stub;

import android.graphics.Bitmap;

public abstract class BitmapDescriptor {
    private BitmapDescriptor() {}

    public static BitmapDescriptor fromBitmap(Bitmap bitmap) {
        return new BitmapDescriptor() {};
    }

    public static BitmapDescriptor fromResource(int resourceId) {
        return new BitmapDescriptor() {};
    }

    public static BitmapDescriptor fromView(android.view.View view) {
        return new BitmapDescriptor() {};
    }

    public static BitmapDescriptor fromPath(String assetPath) {
        return new BitmapDescriptor() {};
    }

    public static BitmapDescriptor defaultMarker() {
        return new BitmapDescriptor() {};
    }

    public static BitmapDescriptor defaultMarker(float hue) {
        return new BitmapDescriptor() {};
    }
}