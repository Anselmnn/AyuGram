package com.tech.ayugram.mlkit.vision.face;

public class Landmark {
    public enum Type {
        LEFT_EYE,
        RIGHT_EYE,
        LEFT_CHEEK,
        RIGHT_CHEEK,
        NOSE_BASE,
        LEFT_MOUTH,
        RIGHT_MOUTH,
        LEFT_EAR,
        RIGHT_EAR,
        LEFT_EYEBROW,
        RIGHT_EYEBROW,
        LEFT_EYE_PUPIL,
        RIGHT_EYE_PUPIL
    }

    public Type getType() { return null; }
    public float getX() { return 0; }
    public float getY() { return 0; }
}