package com.tech.ayugram.maps.stub.model;

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