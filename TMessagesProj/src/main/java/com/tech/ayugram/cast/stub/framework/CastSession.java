package com.tech.ayugram.cast.stub.framework;

import com.tech.ayugram.cast.stub.MediaInfo;
import com.tech.ayugram.cast.stub.MediaLoadOptions;
import com.tech.ayugram.cast.stub.framework.media.RemoteMediaClient;

public class CastSession {
    public void loadMedia(RemoteMediaClient client, com.tech.ayugram.cast.stub.MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds, com.tech.ayugram.cast.stub.MediaLoadOptions options) {}

    public void endSession(boolean stopCasting) {}

    public void addSessionManagerListener(SessionManagerListener listener, CastContext context) {}

    public void removeSessionManagerListener(SessionManagerListener listener) {}

    public RemoteMediaClient getRemoteMediaClient() {
        return new RemoteMediaClient();
    }
}