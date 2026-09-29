package com.tech.ayugram.maps.stub.model;

import java.util.List;

public class Polyline {
    private List<LatLng> points;
    private int color;
    private float width;
    private float zIndex;
    private boolean visible;
    private boolean geodesic;
    private List<PatternItem> pattern;

    public Polyline() {}

    public void setPoints(List<LatLng> points) { this.points = points; }
    public void setColor(int color) { this.color = color; }
    public void setWidth(float width) { this.width = width; }
    public void setZIndex(float zIndex) { this.zIndex = zIndex; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setGeodesic(boolean geodesic) { this.geodesic = geodesic; }
    public void setPattern(List<PatternItem> pattern) { this.pattern = pattern; }
}