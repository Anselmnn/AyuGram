package com.tech.ayugram.ui;

public class CastSync {
    public static final int TYPE_PHOTOVIEWER = 1;
    public static final int TYPE_MUSIC = 2;

    public static boolean isActive() { return false; }
    public static void syncInterface() {}
    public static void check(int type) {}
    public static boolean isUpdatePending() { return false; }
    public static long getPosition() { return -1; }
    public static boolean isPlaying() { return false; }
    public static float getDeviceVolume() { return 1.0f; }
    public static float getVolume() { return 1.0f; }
    public static float getSpeed() { return 1.0f; }
    public static void setPlaying(boolean playing) {}
}