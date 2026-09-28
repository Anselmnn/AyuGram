package com.tech.ayugram.cast.stub.framework;

public interface SessionManagerListener<T> {
    void onSessionStarting(T session);
    void onSessionStarted(T session, String sessionId);
    void onSessionStartFailed(T session, int error);
    void onSessionEnding(T session);
    void onSessionEnded(T session, int error);
    void onSessionResuming(T session, String sessionId);
    void onSessionResumed(T session, boolean wasSuspended);
    void onSessionResumeFailed(T session, int error);
    void onSessionSuspended(T session, int reason);
}