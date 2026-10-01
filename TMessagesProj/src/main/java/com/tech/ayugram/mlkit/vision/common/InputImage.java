package com.tech.ayugram.mlkit.vision.common;

import android.graphics.Bitmap;
import android.media.Image;
import java.nio.ByteBuffer;

public class InputImage {
    private InputImage() {}

    public static InputImage fromBitmap(Bitmap bitmap, int rotationDegrees) {
        return new InputImage();
    }

    public static InputImage fromMediaImage(Image image, int rotationDegrees) {
        return new InputImage();
    }

    public static InputImage fromByteBuffer(ByteBuffer byteBuffer, int width, int height, int rotationDegrees, int format) {
        return new InputImage();
    }

    public static InputImage fromByteArray(byte[] data, int width, int height, int rotationDegrees, int format) {
        return new InputImage();
    }

    public static InputImage fromFilePath(java.io.File file, int rotationDegrees) {
        return new InputImage();
    }

    public Bitmap getBitmapInternal() { return null; }
    public Image getMediaImageInternal() { return null; }
    public ByteBuffer getByteBufferInternal() { return null; }
    public byte[] getByteArrayInternal() { return null; }
    public String getFilePathInternal() { return null; }
    public int getWidthInternal() { return 0; }
    public int getHeightInternal() { return 0; }
    public int getRotationDegreesInternal() { return 0; }
    public int getFormatInternal() { return 0; }
}
