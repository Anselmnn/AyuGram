package com.google.android.gms.cast;

public class MediaInfo {
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;

    public MediaInfo(String contentId, String streamType, String contentType, MediaMetadata metadata, long duration, int streamTypeEnum, MediaMetadata mediaMetadata) {
    }

    public static class Builder {
        public Builder(String contentId, String streamType) { return this; }
        public Builder setContentType(String contentType) { return this; }
        public Builder setMetadata(MediaMetadata metadata) { return this; }
        public Builder setStreamDuration(long duration) { return this; }
        public Builder setStreamType(int streamType) { return this; }
        public MediaInfo build() { return new MediaInfo("", "", "", null, 0, 0, null); }
    }
}