package com.tech.ayugram.maps.stub;

import java.util.ArrayList;
import java.util.List;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.PolygonOptions
 * Google Maps dependency removed in Phase 2
 */
public class PolygonOptions {
    private List<LatLng> points = new ArrayList<>();
    private List<List<LatLng>> holes = new ArrayList<>();
    private float strokeWidth = 10.0f;
    private int strokeColor = 0xFF000000;
    private int fillColor = 0x00000000;
    private float strokePattern;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private boolean geodesic = false;
    private boolean clickable = false;

    public PolygonOptions add(LatLng latLng) {
        points.add(latLng);
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> latLngs) {
        for (LatLng latLng : latLngs) {
            points.add(latLng);
        }
        return this;
    }

    public PolygonOptions addHole(Iterable<LatLng> latLngs) {
        List<LatLng> hole = new ArrayList<>();
        for (LatLng latLng : latLngs) {
            hole.add(latLng);
        }
        holes.add(hole);
        return this;
    }

    public PolygonOptions strokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public PolygonOptions strokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
        return this;
    }

    public PolygonOptions fillColor(int fillColor) {
        this.fillColor = fillColor;
        return this;
    }

    public PolygonOptions strokePattern(float pattern) {
        this.strokePattern = pattern;
        return this;
    }

    public PolygonOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public PolygonOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public PolygonOptions geodesic(boolean geodesic) {
        this.geodesic = geodesic;
        return this;
    }

    public PolygonOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public List<LatLng> getPoints() { return points; }
    public List<List<LatLng>> getHoles() { return holes; }
    public float getStrokeWidth() { return strokeWidth; }
    public int getStrokeColor() { return strokeColor; }
    public int getFillColor() { return fillColor; }
    public float getStrokePattern() { return strokePattern; }
    public float getZIndex() { return zIndex; }
    public boolean isVisible() { return visible; }
    public boolean isGeodesic() { return geodesic; }
    public boolean isClickable() { return clickable; }
}