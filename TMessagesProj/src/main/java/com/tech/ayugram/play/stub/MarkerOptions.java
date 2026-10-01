package com.tech.ayugram.play.stub;

public final class MarkerOptions {
    private LatLng position;
    private String title;
    private String snippet;
    private float alpha = 1.0f;
    private boolean draggable = false;
    private boolean visible = true;
    private boolean flat = false;
    private float rotation = 0.0f;
    private float anchorU = 0.5f;
    private float anchorV = 1.0f;
    private Object icon;
    private Object zIndex;

    public MarkerOptions position(LatLng position) {
        this.position = position;
        return this;
    }

    public LatLng getPosition() {
        return position;
    }

    public MarkerOptions title(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MarkerOptions snippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public String getSnippet() {
        return snippet;
    }

    public MarkerOptions alpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public float getAlpha() {
        return alpha;
    }

    public MarkerOptions draggable(boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public MarkerOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public MarkerOptions flat(boolean flat) {
        this.flat = flat;
        return this;
    }

    public boolean isFlat() {
        return flat;
    }

    public MarkerOptions rotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public float getRotation() {
        return rotation;
    }

    public MarkerOptions anchor(float u, float v) {
        this.anchorU = u;
        this.anchorV = v;
        return this;
    }

    public float getAnchorU() {
        return anchorU;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public MarkerOptions icon(Object icon) {
        this.icon = icon;
        return this;
    }

    public Object getIcon() {
        return icon;
    }

    public MarkerOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public Object getZIndex() {
        return zIndex;
    }
}