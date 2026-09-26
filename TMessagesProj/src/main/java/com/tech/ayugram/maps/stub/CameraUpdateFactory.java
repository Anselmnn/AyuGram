package com.tech.ayugram.maps.stub;

/**
 * Phase 2: Stub for com.tech.ayugram.maps.stub.CameraUpdateFactory
 * Google Maps dependency removed in Phase 2
 */
public class CameraUpdateFactory {
    public static CameraUpdate newLatLng(com.tech.ayugram.maps.stub.LatLng latLng) {
        return CameraUpdate.newLatLng(latLng);
    }

    public static CameraUpdate newLatLngZoom(com.tech.ayugram.maps.stub.LatLng latLng, float zoom) {
        return CameraUpdate.newLatLngZoom(latLng, zoom);
    }

    public static CameraUpdate zoomIn() {
        return CameraUpdate.zoomIn();
    }

    public static CameraUpdate zoomOut() {
        return CameraUpdate.zoomOut();
    }

    public static CameraUpdate zoomTo(float zoom) {
        return CameraUpdate.zoomTo(zoom);
    }

    public static CameraUpdate zoomBy(float amount) {
        return CameraUpdate.zoomBy(amount);
    }

    public static CameraUpdate zoomBy(float amount, float focusX, float focusY) {
        return CameraUpdate.zoomBy(amount, focusX, focusY);
    }

    public static CameraUpdate scrollBy(float xPixel, float yPixel) {
        return CameraUpdate.scrollBy(xPixel, yPixel);
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return CameraUpdate.newCameraPosition(cameraPosition);
    }
}