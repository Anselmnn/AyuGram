package com.tech.ayugram.play.stub;

import java.util.List;

public final class Polyline {
    private String id;
    private List<LatLng> points;
    private int color;
    private float width;
    private int strokePattern;
    private float zIndex;
    private boolean visible;
    private boolean clickable;
    private boolean geodesic;
    private Object tag;
    private boolean startCap;
    private boolean endCap;
    private boolean jointType;

    Polyline(String id) {
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

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
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

    public boolean getStartCap() {
        return startCap;
    }

    public void setStartCap(boolean startCap) {
        this.startCap = startCap;
    }

    public boolean getEndCap() {
        return endCap;
    }

    public void setEndCap(boolean endCap) {
        this.endCap = endCap;
    }

    public boolean getJointType() {
        return jointType;
    }

    public void setJointType(boolean jointType) {
        this.jointType = jointType;
    }

    public void remove() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polyline polyline = (Polyline) o;
        return id.equals(polyline.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}