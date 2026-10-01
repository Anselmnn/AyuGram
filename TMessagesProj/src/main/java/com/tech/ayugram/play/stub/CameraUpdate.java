package com.tech.ayugram.play.stub;

public abstract class CameraUpdate {
    private CameraUpdate() {}

    public static CameraUpdate newLatLng(LatLng latLng) {
        return new CameraUpdate() {};
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float zoom) {
        return new CameraUpdate() {};
    }

    public static CameraUpdate zoomBy(float amount) {
        return new CameraUpdate() {};
    }

    public static CameraUpdate zoomBy(float amount, android.graphics.Point focus) {
        return new CameraUpdate() {};
    }

    public static CameraUpdate zoomIn() {
        return new CameraUpdate() {};
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate() {};
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate() {};
    }

    public static CameraUpdate scrollBy(float xPixel, float yPixel) {
        return new CameraUpdate() {};
    }
}