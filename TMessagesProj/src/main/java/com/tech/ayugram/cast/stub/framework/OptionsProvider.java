package com.tech.ayugram.cast.stub.framework;

import android.content.Context;

public interface OptionsProvider {
    CastOptions getCastOptions(Context context);
    java.util.List<SessionProvider> getAdditionalSessionProviders(Context context);
}