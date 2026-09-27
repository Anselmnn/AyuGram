package com.tech.ayugram.maps.stub.model;

import java.util.List;

public class Polygon {
    private String id;
    private List<LatLng> points;
    private List<List<LatLng>> holes;
    private float strokeWidth;
    private int strokeColor;
    private int fillColor;
    private float strokePattern;
    private float zIndex;
    private boolean visible;
    private boolean geodesic;
    private float clickable;

    public String getId() { return id; }
    public List<LatLng> getPoints() { return points; }
    public List<List<LatLng>> getHoles() { return holes; }
    public void setPoints(List<LatLng> points) { this.points = points; }
    public void setHoles(List<List<LatLng>> holes) { this.holes = holes; }
    public void setStrokeWidth(float strokeWidth) { this.strokeWidth = strokeWidth; }
    public void setStrokeColor(int strokeColor) { this.strokeColor = strokeColor; }
    public void setFillColor(int fillColor) { this.fillColor = fillColor; }
    public void setStrokePattern(float pattern) { this.strokePattern = pattern; }
    public void setZIndex(float zIndex) { this.zIndex = zIndex; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setGeodesic(boolean geodesic) { this.geodesic = geodesic; }
    public void remove() {}
}