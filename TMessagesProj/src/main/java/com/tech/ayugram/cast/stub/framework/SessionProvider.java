package com.tech.ayugram.cast.stub.framework;

public interface SessionProvider {
    void onSessionStarting(CastSession session);
    void onSessionStarted(CastSession session, String sessionId);
    void onSessionStartFailed(CastSession session, int error);
    void onSessionEnding(CastSession session);
    void onSessionEnded(CastSession session, int error);
    void onSessionResuming(CastSession session, String sessionId);
    void onSessionResumed(CastSession session, boolean wasSuspended);
    void onSessionResumeFailed(CastSession session, int error);
    void onSessionSuspended(CastSession session, int reason);
}