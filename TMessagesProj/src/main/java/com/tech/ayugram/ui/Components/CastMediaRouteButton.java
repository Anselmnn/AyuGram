package com.tech.ayugram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// import androidx.mediarouter.app.MediaRouteButton; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteChooserDialog; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteChooserDialogFragment; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteControllerDialog; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteControllerDialogFragment; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteDialogFactory; (Phase 2: remove Cast)
// import androidx.mediarouter.app.MediaRouteDynamicChooserDialog; (Phase 2: remove Cast)
// import androidx.mediarouter.media.MediaRouteSelector; (Phase 2: remove Cast)

import com.tech.ayugram.messenger.R;
import com.tech.ayugram.ui.ActionBar.Theme;

/**
 * Phase 2: Stub implementation of CastMediaRouteButton (Cast/Mediarouter dependency removed)
 */
public class CastMediaRouteButton extends android.widget.ImageButton {

    public CastMediaRouteButton(@NonNull Context context) {
        super(context);
        setVisibility(GONE);
    }

    // Phase 2: Cast removed - all methods are no-ops
    public static class MyMediaRouteChooserDialogFragment {
        // Stub
    }

    public static class MyMediaRouteControllerDialogFragment {
        // Stub
    }

    private boolean lastConnected;
    public boolean isConnected() {
        return false;
    }

    @Override
    public void setBackground(Drawable background) {}

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        checkConnected();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        checkConnected();
    }

    @Override
    public void invalidate() {
        super.invalidate();
        checkConnected();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        checkConnected();
    }

    private void checkConnected() {
        // Stub
    }

    public void stateUpdated(boolean connected) {
        // Stub
    }
}