package com.google.android.gms.cast;

public class MediaQueueItem {
    public static final int PLAYBACK_DURATION_UNKNOWN = -1;
    
    public MediaInfo getMedia() { return null; }
    public double getAutoplay() { return 1.0; }
    public double getStartTime() { return 0; }
    public long getPlaybackDuration() { return -1; }
}