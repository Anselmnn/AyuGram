package com.tech.ayugram.play.stub;

import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions {
    private List<LatLng> vertices = new ArrayList<>();
    private int color = 0xFF000000;
    private float width = 10.0f;
    private int strokePattern = 0;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private boolean clickable = false;
    private boolean geodesic = false;
    private Object tag;
    private boolean jointType = false;
    private boolean startCap = false;
    private boolean endCap = false;

    public PolylineOptions add(LatLng latLng) {
        vertices.add(latLng);
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> latLngs) {
        for (LatLng latLng : latLngs) {
            vertices.add(latLng);
        }
        return this;
    }

    public List<LatLng> getPoints() {
        return vertices;
    }

    public PolylineOptions color(int color) {
        this.color = color;
        return this;
    }

    public int getColor() {
        return color;
    }

    public PolylineOptions width(float width) {
        this.width = width;
        return this;
    }

    public float getWidth() {
        return width;
    }

    public PolylineOptions strokePattern(int pattern) {
        this.strokePattern = pattern;
        return this;
    }

    public int getStrokePattern() {
        return strokePattern;
    }

    public PolylineOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public float getZIndex() {
        return zIndex;
    }

    public PolylineOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public PolylineOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public PolylineOptions geodesic(boolean geodesic) {
        this.geodesic = geodesic;
        return this;
    }

    public boolean isGeodesic() {
        return geodesic;
    }

    public PolylineOptions tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Object getTag() {
        return tag;
    }

    public PolylineOptions startCap(boolean startCap) {
        this.startCap = startCap;
        return this;
    }

    public boolean getStartCap() {
        return startCap;
    }

    public PolylineOptions endCap(boolean endCap) {
        this.endCap = endCap;
        return this;
    }

    public boolean getEndCap() {
        return endCap;
    }

    public PolylineOptions jointType(boolean jointType) {
        this.jointType = jointType;
        return this;
    }

    public boolean getJointType() {
        return jointType;
    }
}