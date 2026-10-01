package com.google.android.gms.cast;

public class MediaInfo {
    private String contentId;
    private String streamType;
    private String contentType;
    private MediaMetadata metadata;
    private long duration;
    private MediaStreamType streamTypeEnum;
    private MediaMetadata mediaMetadata;
    private com.google.android.gms.cast.MediaInfo.Builder builder;

    public MediaInfo(String contentId, String streamType, String contentType, MediaMetadata metadata, long duration, MediaStreamType streamTypeEnum, MediaMetadata mediaMetadata) {
        this.contentId = contentId;
        this.streamType = streamType;
        this.contentType = contentType;
        this.metadata = metadata;
        this.duration = duration;
    }

    public String getContentId() {
        return contentId;
    }

    public String getStreamType() {
        return streamType;
    }

    public String getContentType() {
        return contentType;
    }

    public MediaMetadata getMetadata() {
        return metadata;
    }

    public long getDuration() {
        return duration;
    }

    public static class Builder {
        private String contentId;
        private String streamType;
        private String contentType;
        private MediaMetadata metadata;
        private long duration;

        public Builder(String contentId, String streamType) {
            this.contentId = contentId;
            this.streamType = streamType;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setMetadata(MediaMetadata metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        public MediaInfo build() {
            return new MediaInfo(contentId, streamType, contentType, metadata, duration, null, null);
        }
    }

    public static final String STREAM_TYPE_BUFFERED = "BUFFERED";
    public static final String STREAM_TYPE_LIVE = "LIVE";
    public static final String STREAM_TYPE_UNKNOWN = "UNKNOWN";
}