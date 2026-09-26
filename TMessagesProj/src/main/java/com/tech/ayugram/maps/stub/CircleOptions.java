package com.tech.ayugram.maps.stub;

import com.google.android.gms.maps.model.LatLng;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.CircleOptions
 * Google Maps dependency removed in Phase 2
 */
public class CircleOptions {
    private LatLng center;
    private double radius;
    private float strokeWidth = 10.0f;
    private int strokeColor = 0xFF000000;
    private int fillColor = 0x00000000;
    private float strokePattern;

    public CircleOptions center(LatLng center) {
        this.center = center;
        return this;
    }

    public CircleOptions radius(double radius) {
        this.radius = radius;
        return this;
    }

    public CircleOptions strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public CircleOptions strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public CircleOptions fillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public CircleOptions strokePattern(float pattern) {
        this.strokePattern = pattern;
        return this;
    }

    public LatLng getCenter() { return center; }
    public double getRadius() { return radius; }
    public float getStrokeWidth() { return strokeWidth; }
    public int getStrokeColor() { return strokeColor; }
    public int getFillColor() { return fillColor; }
}