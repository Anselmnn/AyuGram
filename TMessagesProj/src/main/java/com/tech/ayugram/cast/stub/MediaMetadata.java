package com.tech.ayugram.cast.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaMetadata {
    private int type;
    private Map<String, Object> data = new HashMap<>();
    private List<com.google.android.gms.common.images.WebImage> images = new ArrayList<>();

    public MediaMetadata() {}

    public MediaMetadata(int type) {
        this.type = type;
    }

    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    public void putString(String key, String value) {
        data.put(key, value);
    }

    public void putInt(String key, int value) {
        data.put(key, value);
    }

    public void putLong(String key, long value) {
        data.put(key, value);
    }

    public void putBitmap(String key, android.graphics.Bitmap value) {
        data.put(key, value);
    }

    public void addImage(com.google.android.gms.common.images.WebImage image) {
        images.add(image);
    }

    public List<com.google.android.gms.common.images.WebImage> getImages() {
        return images;
    }

    public String getString(String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : null;
    }

    public int getInt(String key) {
        Object value = data.get(key);
        return value instanceof Integer ? (Integer) value : 0;
    }

    public long getLong(String key) {
        Object value = data.get(key);
        return value instanceof Long ? (Long) value : 0;
    }

    public android.graphics.Bitmap getBitmap(String key) {
        Object value = data.get(key);
        return value instanceof android.graphics.Bitmap ? (android.graphics.Bitmap) value : null;
    }

    public static final int MEDIA_TYPE_MOVIE = 0;
    public static final int MEDIA_TYPE_TV_SHOW = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 2;
    public static final int MEDIA_TYPE_PHOTO = 3;
    public static final int MEDIA_TYPE_AUDIO_BOOK = 4;
    public static final int MEDIA_TYPE_LIVE_TV = 5;
    public static final int MEDIA_TYPE_LIVE_RADIO = 6;

    public static final String KEY_TITLE = "title";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_ALBUM_TITLE = "albumTitle";
    public static final String KEY_ALBUM_ARTIST = "albumArtist";
    public static final String KEY_COMPOSER = "composer";
    public static final String KEY_DISC_NUMBER = "discNumber";
    public static final String KEY_TRACK_NUMBER = "trackNumber";
    public static final String KEY_ALBUM_ART = "albumArt";
    public static final String KEY_ART = "art";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_SUBTITLE = "subtitle";
}