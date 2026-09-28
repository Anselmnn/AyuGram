package com.tech.ayugram.cast.stub.framework;

import com.tech.ayugram.cast.stub.framework.media.RemoteMediaClient;
import com.tech.ayugram.cast.stub.CastSession;

public class SessionManager {
    public <T> void addSessionManagerListener(SessionManagerListener<T> listener, T session) {}
    public <T> void removeSessionManagerListener(SessionManagerListener<T> listener, T session) {}

    public void addSessionManagerListener(SessionManagerListener<?> listener, String namespace) {}
    public void removeSessionManagerListener(SessionManagerListener<?> listener, String namespace) {}
}