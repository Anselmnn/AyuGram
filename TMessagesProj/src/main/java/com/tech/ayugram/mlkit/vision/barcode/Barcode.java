package com.tech.ayugram.mlkit.vision.barcode;

import com.tech.ayugram.mlkit.vision.common.PointF;

public class Barcode {
    public static final int QR_CODE = 1;
    public static final int DATA_MATRIX = 2;
    public static final int AZTEC = 4;
    public static final int CODE_128 = 16;
    public static final int CODE_39 = 8;
    public static final int CODE_93 = 32;
    public static final int CODABAR = 64;
    public static final int EAN_13 = 128;
    public static final int EAN_8 = 256;
    public static final int ITF = 512;
    public static final int PDF417 = 1024;
    public static final int UPC_A = 2048;
    public static final int UPC_E = 4096;

    private String rawValue;
    private int valueFormat;
    private String displayValue;
    private int format;
    private PointF[] cornerPoints;

    public String getRawValue() { return rawValue; }
    public void setRawValue(String rawValue) { this.rawValue = rawValue; }
    public int getValueFormat() { return valueFormat; }
    public void setValueFormat(int valueFormat) { this.valueFormat = valueFormat; }
    public String getDisplayValue() { return displayValue; }
    public void setDisplayValue(String displayValue) { this.displayValue = displayValue; }
    public int getFormat() { return format; }
    public void setFormat(int format) { this.format = format; }
    public PointF[] getCornerPoints() { return cornerPoints; }
    public void setCornerPoints(PointF[] cornerPoints) { this.cornerPoints = cornerPoints; }

    public static class PointF {
        public float x;
        public float y;
    }
}
