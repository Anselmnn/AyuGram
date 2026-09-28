package com.tech.ayugram.vision.stub;

import android.graphics.Bitmap;
import java.nio.ByteBuffer;

public class Frame {
    private Bitmap bitmap;
    private ByteBuffer imageData;
    private int width;
    private int height;
    private int format;
    private int rotation;

    private Frame() {}

    public static final int ROTATION_0 = 0;
    public static final int ROTATION_90 = 1;
    public static final int ROTATION_180 = 2;
    public static final int ROTATION_270 = 3;

    public static class Builder {
        private Bitmap bitmap;
        private ByteBuffer imageData;
        private int width;
        private int height;
        private int format;
        private int rotation = ROTATION_0;

        public Builder setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }

        public Builder setRotation(int rotation) {
            this.rotation = rotation;
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
            frame.rotation = this.rotation;
            return frame;
        }
    }

    public Bitmap getBitmap() { return bitmap; }
    public ByteBuffer getImageData() { return imageData; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getFormat() { return format; }
    public int getRotation() { return rotation; }
}