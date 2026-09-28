package com.tech.ayugram.vision.stub.face;

import android.graphics.PointF;

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
    private PointF position;

    public int getType() { return type; }
    public PointF getPosition() { return position; }
    public float getX() { return position != null ? position.x : 0; }
    public float getY() { return position != null ? position.y : 0; }
}