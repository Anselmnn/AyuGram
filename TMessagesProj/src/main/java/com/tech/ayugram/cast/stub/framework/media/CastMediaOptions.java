package com.tech.ayugram.cast.stub.framework.media;

import com.tech.ayugram.cast.stub.MediaInfo;
import com.tech.ayugram.cast.stub.MediaLoadOptions;

public class CastMediaOptions {
    private MediaInfo mediaInfo;
    private MediaLoadOptions loadOptions;
    private boolean autoPlay;
    private long playPosition;
    private long[] activeTrackIds;
    private String customData;

    public CastMediaOptions() {}

    public MediaInfo getMediaInfo() { return mediaInfo; }
    public void setMediaInfo(MediaInfo mediaInfo) { this.mediaInfo = mediaInfo; }
    public MediaLoadOptions getLoadOptions() { return loadOptions; }
    public void setLoadOptions(MediaLoadOptions loadOptions) { this.loadOptions = loadOptions; }
    public boolean getAutoPlay() { return autoPlay; }
    public void setAutoPlay(boolean autoPlay) { this.autoPlay = autoPlay; }
    public long getPlayPosition() { return playPosition; }
    public void setPlayPosition(long playPosition) { this.playPosition = playPosition; }
    public long[] getActiveTrackIds() { return activeTrackIds; }
    public void setActiveTrackIds(long[] activeTrackIds) { this.activeTrackIds = activeTrackIds; }
    public String getCustomData() { return customData; }
    public void setCustomData(String customData) { this.customData = customData; }
}