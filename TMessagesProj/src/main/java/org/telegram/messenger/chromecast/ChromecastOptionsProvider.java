package com.tech.ayugram.messenger.chromecast;

import android.content.Context;

import androidx.annotation.NonNull;

import com.tech.ayugram.cast.stub.CastMediaControlIntent;
import com.tech.ayugram.cast.stub.LaunchOptions;
import com.tech.ayugram.cast.stub.framework.CastOptions;
import com.tech.ayugram.cast.stub.framework.OptionsProvider;
import com.tech.ayugram.cast.stub.framework.SessionProvider;
import com.tech.ayugram.cast.stub.framework.media.CastMediaOptions;

import java.util.List;

public class ChromecastOptionsProvider implements OptionsProvider {
    private static final CastOptions castOptions = new CastOptions.Builder()
        .setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
        .build();

    @NonNull
    @Override
    public CastOptions getCastOptions(@NonNull Context context) {
        return castOptions;
    }

    @Override
    public List<SessionProvider> getAdditionalSessionProviders(@NonNull Context context) {
        return null;
    }
}
