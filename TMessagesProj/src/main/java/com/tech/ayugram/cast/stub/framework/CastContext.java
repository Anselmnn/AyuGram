package com.tech.ayugram.cast.stub.framework;

import android.content.Context;
import androidx.mediarouter.media.MediaRouteSelector;

public class CastContext {
    private CastContext() {}

    public static CastContext getSharedInstance(Context context) {
        return new CastContext();
    }

    public CastSession getSession() {
        return new CastSession();
    }

    public MediaRouteSelector getMergedSelector() {
        return new MediaRouteSelector.Builder().build();
    }

    public void addCastStateListener(CastStateListener listener) {}

    public void removeCastStateListener(CastStateListener listener) {}

    public interface CastStateListener {
        void onCastStateChanged(int state);
    }
}