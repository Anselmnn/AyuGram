package com.tech.ayugram.play.stub;

public final class CircleOptions {
    private LatLng center;
    private double radius;
    private int strokeColor = 0xFF000000;
    private int fillColor = 0x00000000;
    private float strokeWidth = 10.0f;
    private int strokePattern = 0;
    private float zIndex = 0.0f;
    private boolean visible = true;
    private boolean clickable = false;
    private Object tag;

    public CircleOptions center(LatLng center) {
        this.center = center;
        return this;
    }

    public LatLng getCenter() {
        return center;
    }

    public CircleOptions radius(double radius) {
        this.radius = radius;
        return this;
    }

    public double getRadius() {
        return radius;
    }

    public CircleOptions strokeColor(int color) {
        this.strokeColor = color;
        return this;
    }

    public int getStrokeColor() {
        return strokeColor;
    }

    public CircleOptions fillColor(int color) {
        this.fillColor = color;
        return this;
    }

    public int getFillColor() {
        return fillColor;
    }

    public CircleOptions strokeWidth(float width) {
        this.strokeWidth = width;
        return this;
    }

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public CircleOptions strokePattern(int pattern) {
        this.strokePattern = pattern;
        return this;
    }

    public int getStrokePattern() {
        return strokePattern;
    }

    public CircleOptions zIndex(float zIndex) {
        this.zIndex = zIndex;
        return this;
    }

    public float getZIndex() {
        return zIndex;
    }

    public CircleOptions visible(boolean visible) {
        this.visible = visible;
        return this;
    }

    public boolean isVisible() {
        return visible;
    }

    public CircleOptions clickable(boolean clickable) {
        this.clickable = clickable;
        return this;
    }

    public boolean isClickable() {
        return clickable;
    }

    public CircleOptions tag(Object tag) {
        this.tag = tag;
        return this;
    }

    public Object getTag() {
        return tag;
    }
}