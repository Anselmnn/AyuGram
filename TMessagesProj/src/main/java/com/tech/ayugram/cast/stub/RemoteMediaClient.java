package com.tech.ayugram.cast.stub;

public class RemoteMediaClient {
    public void load(MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, MediaLoadOptions options) {}

    public void seek(long position, int resumeState) {}

    public void play() {}

    public void pause() {}

    public void stop() {}

    public void setVolume(double volume) {}

    public void registerCallback(Callback callback) {}

    public void unregisterCallback(Callback callback) {}

    public interface Callback {
        void onStatusUpdated();
        void onMetadataUpdated();
        void onPreloadStatusUpdated();
        void onAdBreakStatusUpdated();
    }
}