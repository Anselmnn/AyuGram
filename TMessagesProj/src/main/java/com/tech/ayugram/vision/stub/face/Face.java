package com.tech.ayugram.vision.stub.face;

import java.util.List;

public class Face {
    private float x;
    private float y;
    private float width;
    private float height;
    private float leftEyeOpenProbability;
    private float rightEyeOpenProbability;
    private float smilingProbability;
    private float eulerY;
    private float eulerZ;
    private List<Landmark> landmarks;

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getLeftEyeOpenProbability() { return leftEyeOpenProbability; }
    public float getRightEyeOpenProbability() { return rightEyeOpenProbability; }
    public float getSmilingProbability() { return smilingProbability; }
    public float getEulerY() { return eulerY; }
    public float getEulerZ() { return eulerZ; }
    public List<Landmark> getLandmarks() { return landmarks; }
}