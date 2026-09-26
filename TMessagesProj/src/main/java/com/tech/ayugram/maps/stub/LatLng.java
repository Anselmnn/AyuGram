package com.tech.ayugram.maps.stub;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.LatLng
 * Google Maps dependency removed in Phase 2
 */
public class LatLng {
    public final double latitude;
    public final double longitude;

    public LatLng(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double latitude() { return latitude; }
    public double longitude() { return longitude; }
}