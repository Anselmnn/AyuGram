package com.google.android.gms.cast;

public class CastSession {
    public void setMessageReceivedCallbacks(String namespace, MessageReceivedCallback callback) {
    }

    public void sendMessage(String namespace, String message) {
    }

    public void sendMessage(String namespace, String message, ResultCallback<Status> callback) {
    }

    public void endSession() {
    }

    public void endSession(boolean stopCasting) {
    }

    public RemoteMediaClient getRemoteMediaClient() {
        return null;
    }

    public void addCastSessionListener(CastSessionListener listener) {
    }

    public void removeCastSessionListener(CastSessionListener listener) {
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String namespace, String message);
    }

    public interface CastSessionListener {
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