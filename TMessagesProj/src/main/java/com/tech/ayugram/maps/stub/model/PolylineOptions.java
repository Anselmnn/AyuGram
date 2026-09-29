package com.tech.ayugram.maps.stub.model;

import java.util.List;

public class PolylineOptions {
    private List<LatLng> points;
    private int color;
    private float width;
    private float zIndex;
    private boolean visible;
    private boolean geodesic;
    private List<PatternItem> pattern;
    private int jointType;
    private int startCap;
    private int endCap;

    public PolylineOptions() {}

    public PolylineOptions add(LatLng latLng) { return this; }
    public PolylineOptions addAll(List<LatLng> points) { return this; }
    public PolylineOptions color(int color) { this.color = color; return this; }
    public PolylineOptions width(float width) { this.width = width; return this; }
    public PolylineOptions zIndex(float zIndex) { this.zIndex = zIndex; return this; }
    public PolylineOptions visible(boolean visible) { this.visible = visible; return this; }
    public PolylineOptions geodesic(boolean geodesic) { this.geodesic = geodesic; return this; }
    public PolylineOptions pattern(List<PatternItem> pattern) { this.pattern = pattern; return this; }
    public PolylineOptions jointType(int jointType) { this.jointType = jointType; return this; }
    public PolylineOptions startCap(int cap) { return this; }
    public PolylineOptions endCap(int cap) { return this; }
    public PolylineOptions clickable(boolean clickable) { return this; }
    public PolylineOptions addAll(Iterable<LatLng> points) { return this; }
}