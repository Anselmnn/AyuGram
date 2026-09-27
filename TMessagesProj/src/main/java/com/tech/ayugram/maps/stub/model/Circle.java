package com.tech.ayugram.maps.stub.model;

import com.tech.ayugram.maps.stub.model.LatLng;

public class Circle {
    private String id;
    private LatLng center;
    private double radius;
    private float strokeWidth;
    private int strokeColor;
    private int fillColor;
    private float strokePattern;

    public String getId() { return id; }
    public LatLng getCenter() { return center; }
    public double getRadius() { return radius; }
    public float getStrokeWidth() { return strokeWidth; }
    public int getStrokeColor() { return strokeColor; }
    public int getFillColor() { return fillColor; }
    public void setCenter(LatLng center) { this.center = center; }
    public void setRadius(double radius) { this.radius = radius; }
    public void setStrokeWidth(float strokeWidth) { this.strokeWidth = strokeWidth; }
    public void setStrokeColor(int strokeColor) { this.strokeColor = strokeColor; }
    public void setFillColor(int fillColor) { this.fillColor = fillColor; }
    public void remove() {}
}