package com.tech.ayugram.play.stub;

import java.util.List;

public final class Polygon {
    private String id;
    private List<LatLng> points;
    private List<List<LatLng>> holes;
    private int strokeColor;
    private int fillColor;
    private float strokeWidth;
    private int strokePattern;
    private float zIndex;
    private boolean visible;
    private boolean clickable;
    private boolean geodesic;
    private Object tag;

    Polygon(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<LatLng> getPoints() {
        return points;
    }

    public void setPoints(List<LatLng> points) {
        this.points = points;
    }

    public List<List<LatLng>> getHoles() {
        return holes;
    }

    public void setHoles(List<List<LatLng>> holes) {
        this.holes = holes;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int color) {
        this.fillColor = color;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
    }

    public int getStrokePattern() {
        return strokePattern;
    }

    public void setStrokePattern(int pattern) {
        this.strokePattern = pattern;
    }

    public float getZIndex() {
        return zIndex;
    }

    public void setZIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public boolean isGeodesic() {
        return geodesic;
    }

    public void setGeodesic(boolean geodesic) {
        this.geodesic = geodesic;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void remove() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polygon polygon = (Polygon) o;
        return id.equals(polygon.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}