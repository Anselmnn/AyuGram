package com.tech.ayugram.play.stub;

public final class CameraUpdateFactory {
    private CameraUpdateFactory() {}

    public static CameraUpdate newLatLng(LatLng latLng) {
        return CameraUpdate.newLatLng(latLng);
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float zoom) {
        return CameraUpdate.newLatLngZoom(latLng, zoom);
    }

    public static CameraUpdate zoomBy(float amount) {
        return CameraUpdate.zoomBy(amount);
    }

    public static CameraUpdate zoomBy(float amount, android.graphics.Point focus) {
        return CameraUpdate.zoomBy(amount, focus);
    }

    public static CameraUpdate zoomIn() {
        return CameraUpdate.zoomIn();
    }

    public static CameraUpdate zoomOut() {
        return CameraUpdate.zoomOut();
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return CameraUpdate.newCameraPosition(cameraPosition);
    }

    public static CameraUpdate scrollBy(float xPixel, float yPixel) {
        return CameraUpdate.scrollBy(xPixel, yPixel);
    }
}