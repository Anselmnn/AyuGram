package com.tech.ayugram.maps.stub.model;

import java.util.List;

public class PolygonOptions {
    private List<LatLng> points;
    private int strokeColor;
    private int fillColor;
    private float strokeWidth;
    private float strokeJointType;
    private List<PatternItem> strokePattern;

    public PolygonOptions() {}

    public PolygonOptions add(LatLng latLng) { return this; }
    public PolygonOptions strokeColor(int color) { this.strokeColor = color; return this; }
    public PolygonOptions fillColor(int color) { this.fillColor = color; return this; }
    public PolygonOptions strokeWidth(float width) { this.strokeWidth = width; return this; }
    public PolygonOptions strokeJointType(int type) { this.strokeJointType = type; return this; }
    public PolygonOptions strokePattern(List<PatternItem> pattern) { this.strokePattern = pattern; return this; }
    public PolygonOptions addHole(List<LatLng> points) { return this; }
    public PolygonOptions geodesic(boolean geodesic) { return this; }
    public PolygonOptions clickable(boolean clickable) { return this; }
    public PolygonOptions visible(boolean visible) { return this; }
    public PolygonOptions zIndex(float zIndex) { return this; }
}