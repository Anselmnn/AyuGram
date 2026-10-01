package com.tech.ayugram.play.stub;

public final class GroundOverlay {
    private String id;
    private LatLng position;
    private float width;
    private float height;
    private LatLng anchor;
    private float bearing;
    private float zIndex;
    private boolean visible;
    private float transparency;
    private boolean clickable;
    private Object tag;

    GroundOverlay(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LatLng getPosition() {
        return position;
    }

    public void setPosition(LatLng position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public LatLng getAnchor() {
        return anchor;
    }

    public void setAnchor(float u, float v) {
        this.anchor = new LatLng(u, v);
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
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

    public float getTransparency() {
        return transparency;
    }

    public void setTransparency(float transparency) {
        this.transparency = transparency;
    }

    public boolean isClickable() {
        return clickable;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public void remove() {
    }

    public void setImage(Object bitmapDescriptor) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroundOverlay that = (GroundOverlay) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}