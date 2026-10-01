package com.google.android.gms.cast.framework;

public class SessionManager {
    private static SessionManager instance;

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public CastSession getCurrentCastSession() {
        return null;
    }

    public void addSessionManagerListener(SessionManagerListener listener, CastSession session) {
    }

    public void removeSessionManagerListener(SessionManagerListener listener, CastSession session) {
    }

    public void startSession(CastSession session) {
    }

    public void endCurrentSession(boolean stopCasting) {
    }

    public interface SessionManagerListener {
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
}