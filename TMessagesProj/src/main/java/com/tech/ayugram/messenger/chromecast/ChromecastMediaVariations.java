package com.tech.ayugram.messenger.chromecast;

import com.tech.ayugram.messenger.MessageObject;
import com.tech.ayugram.tgnet.TLRPC;
import com.tech.ayugram.ui.Components.CubicBezierInterpolator;

import java.io.File;

/**
 * Phase 2: Stub implementation of ChromecastMediaVariations (Cast dependency removed)
 */
public class ChromecastMediaVariations {
    public static final String CONTENT_TYPE_AUDIO = "audio";
    public static final String CONTENT_TYPE_VIDEO = "video";
    public static final String CONTENT_TYPE_IMAGE = "image";

    private final ChromecastMedia media;

    private ChromecastMediaVariations(ChromecastMedia media) {
        this.media = media;
    }

    public static ChromecastMediaVariations of(ChromecastMedia media) {
        return new ChromecastMediaVariations(media);
    }

    public ChromecastMedia getMedia() {
        return media;
    }

    public ChromecastMediaVariations withContentType(String contentType) {
        return this;
    }

    public ChromecastMediaVariations withTitle(String title) {
        return this;
    }

    public ChromecastMediaVariations withSubtitle(String subtitle) {
        return this;
    }

    public ChromecastMediaVariations withImageUrl(String imageUrl) {
        return this;
    }

    public ChromecastMediaVariations withDuration(long duration) {
        return this;
    }
}