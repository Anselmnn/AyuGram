package com.tech.ayugram.messenger.chromecast;

import android.net.Uri;

import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.FileLog;
import com.tech.ayugram.messenger.MessageObject;
import com.tech.ayugram.messenger.R;
import com.tech.ayugram.tgnet.TLRPC;
import com.tech.ayugram.ui.Components.CubicBezierInterpolator;

import java.io.File;

/**
 * Phase 2: Stub implementation of ChromecastMedia (Cast dependency removed)
 */
public class ChromecastMedia {
    public static final String CONTENT_TYPE_AUDIO = "audio";
    public static final String CONTENT_TYPE_VIDEO = "video";
    public static final String CONTENT_TYPE_IMAGE = "image";

    public final String contentId;
    public final String contentType;
    public final Uri contentUri;
    public final String mimeType;
    public final String title;
    public final String subtitle;
    public final String imageUrl;
    public final long duration;
    public final int position;

    private ChromecastMedia(Builder builder) {
        this.contentId = builder.contentId;
        this.contentType = builder.contentType;
        this.contentUri = builder.contentUri;
        this.mimeType = builder.mimeType;
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.imageUrl = builder.imageUrl;
        this.duration = builder.duration;
        this.position = builder.position;
    }

    public static class Builder {
        private String contentId;
        private String contentType;
        private Uri contentUri;
        private String mimeType;
        private String title;
        private String subtitle;
        private String imageUrl;
        private long duration;
        private int position;

        public static Builder fromUri(Uri uri, String contentId, String mime) {
            Builder builder = new Builder();
            builder.contentUri = uri;
            builder.contentId = contentId;
            builder.mimeType = mime;
            return builder;
        }

        public Builder setContentType(String contentType) {
            this.contentType = contentType;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setDuration(long duration) {
            this.duration = duration;
            return this;
        }

        public Builder setPosition(int position) {
            this.position = position;
            return this;
        }

        public ChromecastMedia build() {
            return new ChromecastMedia(this);
        }
    }
}