package com.tech.ayugram.cast.stub;

import android.content.Context;

public class CastContext {
    private CastContext() {}

    public static CastContext getSharedInstance(Context context) {
        return new CastContext();
    }

    public CastSession getSession() {
        return new CastSession();
    }

    public void addCastStateListener(CastStateListener listener) {}

    public void removeCastStateListener(CastStateListener listener) {}

    public interface CastStateListener {
        void onCastStateChanged(int state);
    }
}