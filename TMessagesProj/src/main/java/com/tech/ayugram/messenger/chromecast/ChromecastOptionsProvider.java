package com.tech.ayugram.messenger.chromecast;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;

/**
 * Phase 2: Stub implementation of ChromecastOptionsProvider (Cast dependency removed)
 */
public class ChromecastOptionsProvider implements OptionsProvider {
    @Override
    public CastOptions getCastOptions(@NonNull Context context) {
        // Stub: return null options
        return new CastOptions.Builder()
                .setReceiverApplicationId("stub")
                .build();
    }

    @Override
    public MediaRouteProviderDescriptor getMediaRouteProviderDescriptor(@NonNull Context context) {
        return new MediaRouteProviderDescriptor.Builder().build();
    }
}