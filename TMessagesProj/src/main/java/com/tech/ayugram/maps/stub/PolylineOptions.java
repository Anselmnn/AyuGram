package com.tech.ayugram.maps.stub;

import java.util.ArrayList;
import java.util.List;

/**
 * Phase 2: Stub for com.tech.ayugram.maps.stub.PolylineOptions
 * Google Maps dependency removed in Phase 2
 */
public class PolylineOptions {
    private List<LatLng> points = new ArrayList<>();
    private float width = 10.0f;
    private int color = 0xFF000000;
    private float pattern;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private boolean geodesic = false;
    private boolean clickable = false;
    private int startCap;
    private int endCap;
    private int jointType;

    public PolylineOptions add(LatLng latLng) {
        points.add(latLng);
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> latLngs) {
        for (LatLng latLng : latLngs) {
            points.add(latLng);
        }
        return this;
    }

    public PolylineOptions width(float width) {
        this.width = width;
        return this;
    }

    public PolylineOptions color(int color) {
        this.color = color;
        return this;
    }

    public PolylineOptions pattern(float pattern) {
        this.pattern = pattern;
        return this;
    }

    public PolylineOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public PolylineOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public PolylineOptions geodesic(boolean geodesic) {
        this.geodesic = geodesic;
        return this;
    }

    public PolylineOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public PolylineOptions startCap(int startCap) {
        this.startCap = startCap;
        return this;
    }

    public PolylineOptions endCap(int endCap) {
        this.endCap = endCap;
        return this;
    }

    public PolylineOptions jointType(int jointType) {
        this.jointType = jointType;
        return this;
    }

    public List<LatLng> getPoints() { return points; }
    public float getWidth() { return width; }
    public int getColor() { return color; }
    public float getPattern() { return pattern; }
    public float getZIndex() { return zIndex; }
    public boolean isVisible() { return visible; }
    public boolean isGeodesic() { return geodesic; }
    public boolean isClickable() { return clickable; }
    public int getStartCap() { return startCap; }
    public int getEndCap() { return endCap; }
    public int getJointType() { return jointType; }
}