package com.tech.ayugram.maps.stub;

import java.util.List;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.Polyline
 * Google Maps dependency removed in Phase 2
 */
public class Polyline {
    private String id;
    private List<LatLng> points;
    private float width;
    private int color;
    private float pattern;
    private float zIndex;
    private boolean visible;
    private boolean geodesic;
    private boolean clickable;
    private boolean jointType;

    public String getId() { return id; }
    public List<LatLng> getPoints() { return points; }
    public void setPoints(List<LatLng> points) { this.points = points; }
    public void setWidth(float width) { this.width = width; }
    public void setColor(int color) { this.color = color; }
    public void setPattern(float pattern) { this.pattern = pattern; }
    public void setZIndex(float zIndex) { this.zIndex = zIndex; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setGeodesic(boolean geodesic) { this.geodesic = geodesic; }
    public void setClickable(boolean clickable) { this.clickable = clickable; }
    public void remove() {}
}