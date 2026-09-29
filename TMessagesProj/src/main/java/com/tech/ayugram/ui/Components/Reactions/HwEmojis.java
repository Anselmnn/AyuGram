package com.tech.ayugram.ui.Components.Reactions;

import android.view.View;

public class HwEmojis {
    public static boolean isAvailable() { return false; }
    public static void load() {}
    public static boolean grabIfWeakDevice(View view, Object context) { return false; }
    public static boolean grabIfWeakDevice(Object context) { return false; }
    public static boolean isHwEnabledOrPreparing() { return false; }
    public static void exec() {}
    public static boolean isHwEnabled() { return false; }
    public static boolean isPreparing() { return false; }
    public static boolean grab(View view) { return false; }
    public static boolean grab(Object context) { return false; }
    public static boolean grab(Object context, int flags) { return false; }
    public static void beforePreparing() {}
    public static boolean isFirstOpen() { return false; }
    public static boolean isCascade() { return false; }
    public static void disableHw() {}
    public static void prepare(Runnable callback, boolean flag) {}
    public static void enableHw() {}
}