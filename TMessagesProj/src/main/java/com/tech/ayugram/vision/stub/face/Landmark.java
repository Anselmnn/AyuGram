package com.tech.ayugram.vision.stub.face;

public class Landmark {
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