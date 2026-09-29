package com.tech.ayugram.cast.stub;

public class MediaInfo {
    private String contentId;
    private String streamType;
    private String contentType;
    private MediaMetadata metadata;
    private long streamDuration;
    private MediaTrack[] tracks;
    private TextTrackStyle textTrackStyle;
    private long customData;

    public static final String STREAM_TYPE_BUFFERED = "BUFFERED";
    public static final String STREAM_TYPE_LIVE = "LIVE";
    public static final String STREAM_TYPE_NONE = "NONE";

    public static class Builder {
        private String contentId;
        private String streamType;
        private String contentType;
        private MediaMetadata metadata;
        private long streamDuration;
        private MediaTrack[] tracks;
        private TextTrackStyle textTrackStyle;
        private long customData;

        public Builder(String contentId) {
            this.contentId = contentId;
            this.streamType = STREAM_TYPE_BUFFERED;
        }

        public Builder(String contentId, String streamType) {
            this.contentId = contentId;
            this.streamType = streamType;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setStreamType(String streamType) {
            this.streamType = streamType;
            return this;
        }

        public Builder setMetadata(MediaMetadata metadata) {
            this.metadata = metadata;
            return this;
        }

        public Builder setStreamDuration(long streamDuration) {
            this.streamDuration = streamDuration;
            return this;
        }

        public Builder setTracks(MediaTrack[] tracks) {
            this.tracks = tracks;
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.textTrackStyle = textTrackStyle;
            return this;
        }

        public Builder setCustomData(long customData) {
            this.customData = customData;
            return this;
        }

        public MediaInfo build() {
            MediaInfo mediaInfo = new MediaInfo();
            mediaInfo.contentId = this.contentId;
            mediaInfo.streamType = this.streamType;
            mediaInfo.contentType = this.contentType;
            mediaInfo.metadata = this.metadata;
            mediaInfo.streamDuration = this.streamDuration;
            mediaInfo.tracks = this.tracks;
            mediaInfo.textTrackStyle = this.textTrackStyle;
            mediaInfo.customData = this.customData;
            return mediaInfo;
        }
    }

    public String getContentId() { return contentId; }
    public String getStreamType() { return streamType; }
    public String getContentType() { return contentType; }
    public MediaMetadata getMetadata() { return metadata; }
    public long getStreamDuration() { return streamDuration; }
    public MediaTrack[] getTracks() { return tracks; }
    public TextTrackStyle getTextTrackStyle() { return textTrackStyle; }
    public long getCustomData() { return customData; }

    public static class MediaTrack {
        private long id;
        private int type;
        private String contentId;
        private String contentType;
        private String language;
        private String name;
        private String subtype;

        public long getId() { return id; }
        public int getType() { return type; }
        public String getContentId() { return contentId; }
        public String getContentType() { return contentType; }
        public String getLanguage() { return language; }
        public String getName() { return name; }
        public String getSubtype() { return subtype; }
    }

    public static class TextTrackStyle {
        private int foregroundColor;
        private int backgroundColor;
        private int edgeColor;
        private int windowColor;
        private int edgeType;
        private int fontScale;

        public int getForegroundColor() { return foregroundColor; }
        public int getBackgroundColor() { return backgroundColor; }
        public int getEdgeColor() { return edgeColor; }
        public int getWindowColor() { return windowColor; }
        public int getEdgeType() { return edgeType; }
        public int getFontScale() { return fontScale; }
    }
}