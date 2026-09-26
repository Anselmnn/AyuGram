package com.tech.ayugram.cast.stub;

import com.tech.ayugram.cast.stub.MediaError;

/**
 * Phase 2: Stub for com.google.android.gms.cast.framework.CastSession
 * Cast dependency removed in Phase 2
 */
public class CastSession {
    public void loadMedia(RemoteMediaClient client, com.tech.ayugram.cast.stub.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.tech.ayugram.cast.stub.MediaLoadOptions options) {
        // No-op
    }

    public void endSession(boolean stopCasting) {
        // No-op
    }

    public void addSessionManagerListener(SessionManagerListener listener, com.tech.ayugram.cast.stub.CastContext context) {
        // No-op
    }

    public void removeSessionManagerListener(SessionManagerListener listener) {
        // No-op
    }

    public RemoteMediaClient getRemoteMediaClient() {
        return new RemoteMediaClient();
    }
}