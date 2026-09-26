package com.tech.ayugram.maps.stub;

import android.content.Context;
import android.os.Bundle;

/**
 * Phase 2: Stub for com.tech.ayugram.maps.stub.CameraUpdate
 * Google Maps dependency removed in Phase 2
 */
public class CameraUpdate {
    public static CameraUpdate newLatLng(com.tech.ayugram.maps.stub.LatLng latLng) {
        return new CameraUpdate();
    }

    public static CameraUpdate newLatLngZoom(com.tech.ayugram.maps.stub.LatLng latLng, float zoom) {
        return new CameraUpdate();
    }

    public static CameraUpdate zoomIn() {
        return new CameraUpdate();
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate();
    }

    public static CameraUpdate zoomTo(float zoom) {
        return new CameraUpdate();
    }

    public static CameraUpdate zoomBy(float amount) {
        return new CameraUpdate();
    }

    public static CameraUpdate zoomBy(float amount, float focusX, float focusY) {
        return new CameraUpdate();
    }

    public static CameraUpdate scrollBy(float xPixel, float yPixel) {
        return new CameraUpdate();
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        return new CameraUpdate();
    }
}