package com.tech.ayugram.maps.stub;

import com.tech.ayugram.maps.stub.LatLng;

/**
 * Phase 2: Stub for com.tech.ayugram.maps.stub.MarkerOptions
 * Google Maps dependency removed in Phase 2
 */
public class MarkerOptions {
    private LatLng position;
    private String title;
    private String snippet;
    private float alpha = 1.0f;
    private boolean visible = true;
    private boolean draggable = false;
    private float anchorU = 0.5f;
    private float anchorV = 1.0f;
    private float rotation = 0.0f;
    private boolean flat = false;

    public MarkerOptions position(LatLng position) {
        this.position = position;
        return this;
    }

    public MarkerOptions title(String title) {
        this.title = title;
        return this;
    }

    public MarkerOptions snippet(String snippet) {
        this.snippet = snippet;
        return this;
    }

    public MarkerOptions alpha(float alpha) {
        this.alpha = alpha;
        return this;
    }

    public MarkerOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public MarkerOptions draggable(boolean draggable) {
        this.draggable = draggable;
        return this;
    }

    public MarkerOptions anchor(float u, float v) {
        this.anchorU = u;
        this.anchorV = v;
        return this;
    }

    public MarkerOptions rotation(float rotation) {
        this.rotation = rotation;
        return this;
    }

    public MarkerOptions flat(boolean flat) {
        this.flat = flat;
        return this;
    }

    public LatLng getPosition() { return position; }
    public String getTitle() { return title; }
    public String getSnippet() { return snippet; }
    public float getAlpha() { return alpha; }
    public boolean isVisible() { return visible; }
    public boolean isDraggable() { return draggable; }
    public float getAnchorU() { return anchorU; }
    public float getAnchorV() { return anchorV; }
    public float getRotation() { return rotation; }
    public boolean isFlat() { return flat; }
}