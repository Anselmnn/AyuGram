package com.tech.ayugram.play.stub;

public final class Circle {
    private String id;
    private LatLng center;
    private double radius;
    private int strokeColor;
    private int fillColor;
    private float strokeWidth;
    private int strokePattern;
    private float zIndex;
    private boolean visible;
    private boolean clickable;
    private Object tag;

    Circle(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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
        Circle circle = (Circle) o;
        return id.equals(circle.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}