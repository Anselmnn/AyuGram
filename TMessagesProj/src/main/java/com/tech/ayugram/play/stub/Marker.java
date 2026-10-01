package com.tech.ayugram.play.stub;

import android.graphics.Point;
import android.view.View;

public final class Marker {
    private String id;
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
    private Object tag;
    private Object zIndex;
    private boolean infoWindowShown = false;

    Marker(String id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isFlat() {
        return flat;
    }

    public void setFlat(boolean flat) {
        this.flat = flat;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public float getAnchorU() {
        return anchorU;
    }

    public float getAnchorV() {
        return anchorV;
    }

    public void setAnchor(float anchorU, float anchorV) {
        this.anchorU = anchorU;
        this.anchorV = anchorV;
    }

    public Object getIcon() {
        return icon;
    }

    public void setIcon(Object icon) {
        this.icon = icon;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public Object getZIndex() {
        return zIndex;
    }

    public void setZIndex(float zIndex) {
        this.zIndex = zIndex;
    }

    public boolean isInfoWindowShown() {
        return infoWindowShown;
    }

    public void showInfoWindow() {
        this.infoWindowShown = true;
    }

    public void hideInfoWindow() {
        this.infoWindowShown = false;
    }

    public void remove() {
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marker marker = (Marker) o;
        return id.equals(marker.id);
    }

    public int hashCode() {
        return id.hashCode();
    }
}