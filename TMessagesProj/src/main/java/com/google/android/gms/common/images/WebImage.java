package com.google.android.gms.common.images;

import android.net.Uri;

public class WebImage {
    private final Uri uri;
    private final int width;
    private final int height;

    public WebImage(Uri uri) {
        this(uri, 0, 0);
    }

    public WebImage(Uri uri, int width, int height) {
        this.uri = uri;
        this.width = width;
        this.height = height;
    }

    public Uri getUrl() { return uri; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
}