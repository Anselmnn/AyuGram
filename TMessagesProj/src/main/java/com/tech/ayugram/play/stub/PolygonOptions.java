package com.tech.ayugram.play.stub;

import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions {
    private List<LatLng> vertices = new ArrayList<>();
    private List<List<LatLng>> holes = new ArrayList<>();
    private int strokeColor = 0xFF000000;
    private int fillColor = 0x00000000;
    private float strokeWidth = 10.0f;
    private int strokePattern = 0;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private boolean clickable = false;
    private boolean geodesic = false;
    private Object tag;

    public PolygonOptions add(LatLng latLng) {
        vertices.add(latLng);
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> latLngs) {
        for (LatLng latLng : latLngs) {
            vertices.add(latLng);
        }
        return this;
    }

    public List<LatLng> getPoints() {
        return vertices;
    }

    public PolygonOptions addHole(Iterable<LatLng> hole) {
        List<LatLng> holeList = new ArrayList<>();
        for (LatLng latLng : hole) {
            holeList.add(latLng);
        }
        holes.add(holeList);
        return this;
    }

    public List<List<LatLng>> getHoles() {
        return holes;
    }

    public PolygonOptions strokeColor(int color) {
        this.strokeColor = color;
        return this;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public PolygonOptions fillColor(int color) {
        this.fillColor = color;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public PolygonOptions strokeWidth(float width) {
        this.strokeWidth = width;
        return this;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public PolygonOptions strokePattern(int pattern) {
        this.strokePattern = pattern;
        return this;
    }

    public int getStrokePattern() {
        return strokePattern;
    }

    public PolygonOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public float getZIndex() {
        return zIndex;
    }

    public PolygonOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public PolygonOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public PolygonOptions geodesic(boolean geodesic) {
        this.geodesic = geodesic;
        return this;
    }

    public boolean isGeodesic() {
        return geodesic;
    }

    public PolygonOptions tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Object getTag() {
        return tag;
    }
}