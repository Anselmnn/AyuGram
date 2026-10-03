package com.google.android.gms.cast;

public class MediaMetadata {
    public static final int MEDIA_TYPE_MOVIE = 1;
    public static final int MEDIA_TYPE_TV_SHOW = 2;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 3;
    public static final int MEDIA_TYPE_PHOTO = 4;
    public static final int MEDIA_TYPE_USER = 5;
    public static final int MEDIA_TYPE_GENERIC = 0;

    public void putString(String key, String value) {}
    public String getString(String key) { return null; }
    public void putLong(String key, long value) {}
    public long getLong(String key) { return 0; }
}