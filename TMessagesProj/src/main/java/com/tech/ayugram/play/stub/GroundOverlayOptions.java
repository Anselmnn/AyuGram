package com.tech.ayugram.play.stub;

public final class GroundOverlayOptions {
    private Object image;
    private LatLng position;
    private float width;
    private float height;
    private LatLng anchor = new LatLng(0.5, 0.5);
    private float bearing = 0.0f;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private float transparency = 0.0f;
    private boolean clickable = false;
    private Object tag;

    public GroundOverlayOptions image(Object bitmapDescriptor) {
        this.image = bitmapDescriptor;
        return this;
    }

    public Object getImage() {
        return image;
    }

    public GroundOverlayOptions position(LatLng latLng, float width) {
        this.position = latLng;
        this.width = width;
        return this;
    }

    public GroundOverlayOptions position(LatLng latLng, float width, float height) {
        this.position = latLng;
        this.width = width;
        this.height = height;
        return this;
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds bounds) {
        return this;
    }

    public LatLng getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public GroundOverlayOptions anchor(float u, float v) {
        this.anchor = new LatLng(u, v);
        return this;
    }

    public LatLng getAnchor() {
        return anchor;
    }

    public GroundOverlayOptions bearing(float bearing) {
        this.bearing = bearing;
        return this;
    }

    public float getBearing() {
        return bearing;
    }

    public GroundOverlayOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public float getZIndex() {
        return zIndex;
    }

    public GroundOverlayOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public GroundOverlayOptions transparency(float transparency) {
        this.transparency = transparency;
        return this;
    }

    public float getTransparency() {
        return transparency;
    }

    public GroundOverlayOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public GroundOverlayOptions tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Object getTag() {
        return tag;
    }
}