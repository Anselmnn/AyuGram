package com.tech.ayugram.vision.stub;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

/**
 * Phase 2: Stub for com.google.android.gms.vision.Frame
 * Google Vision dependency removed in Phase 2
 */
public class Frame {
    private Bitmap bitmap;
    private ByteBuffer imageData;
    private int width;
    private int height;
    private int format;

    private Frame() {}

    public static class Builder {
        private Bitmap bitmap;
        private ByteBuffer imageData;
        private int width;
        private int height;
        private int format;

        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder setImageData(ByteBuffer imageData, int width, int height, int format) {
            this.imageData = imageData;
            this.width = width;
            this.height = height;
            this.format = format;
            return this;
        }

        public Frame build() {
            Frame frame = new Frame();
            frame.bitmap = this.bitmap;
            frame.imageData = this.imageData;
            frame.width = this.width;
            frame.height = this.height;
            frame.format = this.format;
            return frame;
        }
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public ByteBuffer getImageData() {
        return imageData;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getFormat() {
        return format;
    }
}