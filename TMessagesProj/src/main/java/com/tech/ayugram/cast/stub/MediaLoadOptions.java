package com.tech.ayugram.cast.stub;

/**
 * Phase 2: Stub for com.google.android.gms.cast.MediaLoadOptions
 * Cast dependency removed in Phase 2
 */
public class MediaLoadOptions {
    private boolean autoplay;
    private long playPosition;
    private long[] activeTrackIds;
    private String customData;

    public MediaLoadOptions() {
        this.autoplay = true;
    }

    public static class Builder {
        private boolean autoplay = true;
        private long playPosition = 0;
        private long[] activeTrackIds;
        private String customData;

        public Builder setAutoplay(boolean autoplay) {
            this.autoplay = autoplay;
            return this;
        }

        public Builder setPlayPosition(long playPosition) {
            this.playPosition = playPosition;
            return this;
        }

        public Builder setActiveTrackIds(long[] activeTrackIds) {
            this.activeTrackIds = activeTrackIds;
            return this;
        }

        public Builder setCustomData(String customData) {
            this.customData = customData;
            return this;
        }

        public MediaLoadOptions build() {
            MediaLoadOptions options = new MediaLoadOptions();
            options.autoplay = this.autoplay;
            options.playPosition = this.playPosition;
            options.activeTrackIds = this.activeTrackIds;
            options.customData = this.customData;
            return options;
        }
    }

    public boolean getAutoplay() { return autoplay; }
    public long getPlayPosition() { return playPosition; }
    public long[] getActiveTrackIds() { return activeTrackIds; }
    public String getCustomData() { return customData; }
}