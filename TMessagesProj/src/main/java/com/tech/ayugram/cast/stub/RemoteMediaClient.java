package com.tech.ayugram.cast.stub;

import com.tech.ayugram.cast.stub.MediaError;

/**
 * Phase 2: Stub for com.google.android.gms.cast.framework.media.RemoteMediaClient
 * Cast dependency removed in Phase 2
 */
public class RemoteMediaClient {
    public void load(com.tech.ayugram.cast.stub.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.tech.ayugram.cast.stub.MediaLoadOptions options) {
        // No-op
    }

    public void seek(long position, int resumeState) {
        // No-op
    }

    public void play() {
        // No-op
    }

    public void pause() {
        // No-op
    }

    public void stop() {
        // No-op
    }

    public void setVolume(double volume) {
        // No-op
    }

    public void registerCallback(Callback callback) {
        // No-op
    }

    public void unregisterCallback(Callback callback) {
        // No-op
    }

    public interface Callback {
        void onStatusUpdated();
        void onMetadataUpdated();
        void onPreloadStatusUpdated();
        void onAdBreakStatusUpdated();
    }
}