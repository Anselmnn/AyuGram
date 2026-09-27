package com.tech.ayugram.cast.stub.framework;

public class CastSession {
    public void loadMedia(RemoteMediaClient client, com.tech.ayugram.cast.stub.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.tech.ayugram.cast.stub.MediaLoadOptions options) {}

    public void endSession(boolean stopCasting) {}

    public void addSessionManagerListener(SessionManagerListener listener, CastContext context) {}

    public void removeSessionManagerListener(SessionManagerListener listener) {}

    public RemoteMediaClient getRemoteMediaClient() {
        return new RemoteMediaClient();
    }
}