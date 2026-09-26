package com.tech.ayugram.vision.stub;

import android.util.SparseArray;

/**
 * Phase 2: Stub for com.google.android.gms.vision.barcode.Barcode
 * Google Vision dependency removed in Phase 2
 */
public class Barcode {
    public static final int QR_CODE = 1;
    public static final int DATA_MATRIX = 2;
    public static final int AZTEC = 4;
    public static final int CODE_39 = 8;
    public static final int CODE_93 = 16;
    public static final int CODE_128 = 32;
    public static final int EAN_8 = 64;
    public static final int EAN_13 = 128;
    public static final int ITF = 256;
    public static final int PDF417 = 512;
    public static final int DRIVER_LICENSE = 1024;
    public static final int UPC_A = 1024;
    public static final int UPC_E = 2048;

    private String rawValue;
    private int valueFormat;
    private String displayValue;
    private int format;
    private CornerPoint[] cornerPoints;

    public String getRawValue() {
        return rawValue;
    }

    public void setRawValue(String rawValue) {
        this.rawValue = rawValue;
    }

    public int getValueFormat() {
        return valueFormat;
    }

    public void setValueFormat(int valueFormat) {
        this.valueFormat = valueFormat;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public CornerPoint[] getCornerPoints() {
        return cornerPoints;
    }

    public void setCornerPoints(CornerPoint[] cornerPoints) {
        this.cornerPoints = cornerPoints;
    }

    public static class CornerPoint {
        public float x;
        public float y;
    }
}