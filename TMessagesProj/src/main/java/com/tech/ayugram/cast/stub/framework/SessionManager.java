package com.tech.ayugram.cast.stub.framework;

import com.tech.ayugram.cast.stub.framework.media.RemoteMediaClient;
import com.tech.ayugram.cast.stub.CastSession;

public class SessionManager {
    public void addSessionManagerListener(SessionManagerListener listener, CastSession session) {}
    public void removeSessionManagerListener(SessionManagerListener listener, CastSession session) {}

    public void addSessionManagerListener(SessionManagerListener listener, String namespace) {}
    public void removeSessionManagerListener(SessionManagerListener listener, String namespace) {}
}