package com.tech.ayugram.cast.stub;

public class CastSession {
    public void loadMedia(RemoteMediaClient client, MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, MediaLoadOptions options) {}

    public void endSession(boolean stopCasting) {}

    public void addSessionManagerListener(SessionManagerListener listener, CastContext context) {}

    public void removeSessionManagerListener(SessionManagerListener listener) {}

    public RemoteMediaClient getRemoteMediaClient() {
        return new RemoteMediaClient();
    }
}