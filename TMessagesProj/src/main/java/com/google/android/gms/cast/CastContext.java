package com.google.android.gms.cast;

public class CastContext {
    private static CastContext instance;

    public static CastContext getSharedInstance(android.content.Context context) {
        if (instance == null) {
            instance = new CastContext();
        }
        return instance;
    }

    public CastSession getSession() {
        return null;
    }

    public void setReceiverApplicationStatusText(String text) {
    }

    public String getReceiverApplicationStatusText() {
        return null;
    }

    public void addCastStateListener(CastStateListener listener) {
    }

    public void removeCastStateListener(CastStateListener listener) {
    }

    public interface CastStateListener {
        void onCastStateChanged(int newState);
    }

    public static final int CAST_STATE_NO_DEVICES_AVAILABLE = 0;
    public static final int CAST_STATE_CONNECTING = 1;
    public static final int CAST_STATE_CONNECTED = 2;
    public static final int CAST_STATE_NOT_CONNECTED = 3;
}