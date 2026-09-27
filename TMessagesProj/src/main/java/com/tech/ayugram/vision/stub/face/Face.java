package com.tech.ayugram.vision.stub.face;

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
    private Landmark[] landmarks;

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getLeftEyeOpenProbability() { return leftEyeOpenProbability; }
    public float getRightEyeOpenProbability() { return rightEyeOpenProbability; }
    public float getSmilingProbability() { return smilingProbability; }
    public float getEulerY() { return eulerY; }
    public float getEulerZ() { return eulerZ; }
    public Landmark[] getLandmarks() { return landmarks; }

    public static class Landmark {
        public static final int LEFT_EYE = 0;
        public static final int RIGHT_EYE = 1;
        public static final int NOSE_BASE = 2;
        public static final int LEFT_EYE_LEFT = 3;
        public static final int LEFT_EYE_RIGHT = 4;
        public static final int RIGHT_EYE_LEFT = 5;
        public static final int RIGHT_EYE_RIGHT = 6;
        public static final int MOUTH_LEFT = 7;
        public static final int MOUTH_RIGHT = 8;
        public static final int MOUTH_CENTER = 9;
        public static final int NOSE_TIP = 10;

        private int type;
        private float x;
        private float y;

        public int getType() { return type; }
        public float getX() { return x; }
        public float getY() { return y; }
    }
}