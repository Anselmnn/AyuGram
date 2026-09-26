package com.tech.ayugram.cast.stub;

import com.google.android.gms.cast.MediaError;

/**
 * Phase 2: Stub for com.google.android.gms.cast.framework.CastSession
 * Cast dependency removed in Phase 2
 */
public class CastSession {
    public void loadMedia(RemoteMediaClient client, com.google.android.gms.cast.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.google.android.gms.cast.MediaLoadOptions options) {
        // No-op
    }

    public void endSession(boolean stopCasting) {
        // No-op
    }

    public void addSessionManagerListener(SessionManagerListener listener, com.google.android.gms.cast.framework.CastContext context) {
        // No-op
    }

    public void removeSessionManagerListener(SessionManagerListener listener) {
        // No-op
    }

    public RemoteMediaClient getRemoteMediaClient() {
        return new RemoteMediaClient();
    }
}