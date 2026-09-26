package com.tech.ayugram.maps.stub;

import com.google.android.gms.maps.model.LatLng;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.Marker
 * Google Maps dependency removed in Phase 2
 */
public class Marker {
    private String id;
    private LatLng position;
    private String title;
    private String snippet;
    private float alpha;
    private boolean visible;
    private boolean draggable;
    private float anchorU;
    private float anchorV;
    private float rotation;
    private boolean flat;
    private Object tag;

    public String getId() { return id; }
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
    public Object getTag() { return tag; }

    public void setPosition(LatLng position) { this.position = position; }
    public void setTitle(String title) { this.title = title; }
    public void setSnippet(String snippet) { this.snippet = snippet; }
    public void setAlpha(float alpha) { this.alpha = alpha; }
    public void setVisible(boolean visible) { this.visible = visible; }
    public void setDraggable(boolean draggable) { this.draggable = draggable; }
    public void setAnchor(float anchorU, float anchorV) { this.anchorU = anchorU; this.anchorV = anchorV; }
    public void setRotation(float rotation) { this.rotation = rotation; }
    public void setFlat(boolean flat) { this.flat = flat; }
    public void setTag(Object tag) { this.tag = tag; }
    public void remove() {}
    public void showInfoWindow() {}
    public void hideInfoWindow() {}
    public boolean isInfoWindowShown() { return false; }
    public boolean equals(Object obj) { return super.equals(obj); }
    public int hashCode() { return super.hashCode(); }
}