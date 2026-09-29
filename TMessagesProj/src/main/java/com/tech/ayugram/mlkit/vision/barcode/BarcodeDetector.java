package com.tech.ayugram.mlkit.vision.barcode;

import android.content.Context;
import android.util.SparseArray;
import com.tech.ayugram.mlkit.vision.common.InputImage;

public class BarcodeDetector {
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

    private BarcodeDetector() {}

    public static class Builder {
        private Context context;
        private int barcodeFormats = 0;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setBarcodeFormats(int formats) {
            this.barcodeFormats = formats;
            return this;
        }

        public BarcodeDetector build() {
            return new BarcodeDetector();
        }
    }

    public SparseArray<Barcode> detect(InputImage image) {
        return new SparseArray<>();
    }

    public void close() {}
}
