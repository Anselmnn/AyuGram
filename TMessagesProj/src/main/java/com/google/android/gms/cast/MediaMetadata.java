package com.google.android.gms.cast;

import android.os.Bundle;

public class MediaMetadata {
    public static final int MEDIA_TYPE_MOVIE = 0;
    public static final int MEDIA_TYPE_TV_SHOW = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 2;
    public static final int MEDIA_TYPE_PHOTO = 3;
    public static final int MEDIA_TYPE_USER = 4;
    public static final int MEDIA_TYPE_GENERIC = 5;

    private int mediaType;
    private Bundle bundle;

    public MediaMetadata(int mediaType) {
        this.mediaType = mediaType;
        this.bundle = new Bundle();
    }

    public int getMediaType() {
        return mediaType;
    }

    public void putString(String key, String value) {
        bundle.putString(key, value);
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public void putLong(String key, long value) {
        bundle.putLong(key, value);
    }

    public long getLong(String key) {
        return bundle.getLong(key);
    }

    public void putDouble(String key, double value) {
        bundle.putDouble(key, value);
    }

    public double getDouble(String key) {
        return bundle.getDouble(key);
    }

    public void putBitmap(String key, android.graphics.Bitmap bitmap) {
        bundle.putParcelable(key, bitmap);
    }

    public android.graphics.Bitmap getBitmap(String key) {
        return bundle.getParcelable(key);
    }

    public void putAll(Bundle bundle) {
        this.bundle.putAll(bundle);
    }

    public Bundle getBundle() {
        return bundle;
    }

    public static final String KEY_TITLE = "title";
    public static final String KEY_SUBTITLE = "subtitle";
    public static final String KEY_STUDIO = "studio";
    public static final String KEY_BROADCAST_DATE = "broadcastDate";
    public static final String KEY_RELEASE_DATE = "releaseDate";
    public static final String KEY_ALBUM_NAME = "albumName";
    public static final String KEY_ALBUM_ARTIST = "albumArtist";
    public static final String KEY_ARTIST = "artist";
    public static final String KEY_COMPOSER = "composer";
    public static final String KEY_TRACK_NUMBER = "trackNumber";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_WIDTH = "width";
    public static final String KEY_HEIGHT = "height";
    public static final String KEY_LOCATION_NAME = "locationName";
    public static final String KEY_LOCATION_LATITUDE = "locationLatitude";
    public static final String KEY_LOCATION_LONGITUDE = "locationLongitude";
}