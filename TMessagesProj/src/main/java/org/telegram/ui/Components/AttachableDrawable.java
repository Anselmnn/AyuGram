package com.tech.ayugram.ui.Components;

import android.view.View;

import com.tech.ayugram.messenger.ImageReceiver;

public interface AttachableDrawable {
    void onAttachedToWindow(ImageReceiver parent);
    void onDetachedFromWindow(ImageReceiver parent);

    default void setParent(View view) {}
}
