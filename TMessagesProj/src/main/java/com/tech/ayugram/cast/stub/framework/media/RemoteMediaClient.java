package com.tech.ayugram.cast.stub.framework.media;

import com.tech.ayugram.cast.stub.MediaError;

public class RemoteMediaClient {
    public void load(com.tech.ayugram.cast.stub.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.tech.ayugram.cast.stub.MediaLoadOptions options) {}

    public void load(com.tech.ayugram.cast.stub.MediaInfo mediaInfo, com.tech.ayugram.cast.stub.MediaLoadOptions options) {}

    public void seek(long position, int resumeState) {}

    public void play() {}

    public void pause() {}

    public void stop() {}

    public void setVolume(double volume) {}

    public void registerCallback(Callback callback) {}

    public void unregisterCallback(Callback callback) {}

    public int getIdleReason() { return 0; }

    public static abstract class Callback {
        public void onStatusUpdated() {}
        public void onMetadataUpdated() {}
        public void onPreloadStatusUpdated() {}
        public void onAdBreakStatusUpdated() {}
        public void onMediaError(MediaError mediaError) {}
        public void onQueueStatusUpdated() {}
        public void onSendingRemoteMediaRequest() {}
    }
}