package com.tech.ayugram.cast.stub;

import android.content.Context;

/**
 * Phase 2: Stub for com.google.android.gms.cast.framework.CastContext
 * Cast dependency removed in Phase 2
 */
public class CastContext {
    private CastContext() {}

    public static CastContext getSharedInstance(Context context) {
        return new CastContext();
    }

    public CastSession getSession() {
        return new CastSession();
    }

    public void addCastStateListener(CastStateListener listener) {
        // No-op
    }

    public void removeCastStateListener(CastStateListener listener) {
        // No-op
    }

    public interface CastStateListener {
        void onCastStateChanged(int state);
    }
}