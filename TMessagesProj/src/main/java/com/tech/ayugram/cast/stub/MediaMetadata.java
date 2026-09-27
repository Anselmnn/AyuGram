package com.tech.ayugram.cast.stub;

public class MediaMetadata {
    private int type;
    private String title;
    private String subtitle;
    private String[] images;

    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSubtitle() { return subtitle; }
    public void setSubtitle(String subtitle) { this.subtitle = subtitle; }

    public String[] getImages() { return images; }
    public void setImages(String[] images) { this.images = images; }

    public static final int MEDIA_TYPE_MOVIE = 0;
    public static final int MEDIA_TYPE_TV_SHOW = 1;
    public static final int MEDIA_TYPE_MUSIC_TRACK = 2;
    public static final int MEDIA_TYPE_PHOTO = 3;
    public static final int MEDIA_TYPE_AUDIO_BOOK = 4;
    public static final int MEDIA_TYPE_LIVE_TV = 5;
    public static final int MEDIA_TYPE_LIVE_RADIO = 6;
}