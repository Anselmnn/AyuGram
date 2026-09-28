package com.tech.ayugram.vision.stub.barcode;

import android.content.Context;
import android.util.SparseArray;

public class BarcodeDetector {
    private Context context;
    private int barcodeFormats;

    private BarcodeDetector(Context context) {
        this.context = context;
    }

    public static class Builder {
        private Context context;
        private int barcodeFormats;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setBarcodeFormats(int formats) {
            this.barcodeFormats = formats;
            return this;
        }

        public BarcodeDetector build() {
            BarcodeDetector detector = new BarcodeDetector(context);
            detector.barcodeFormats = this.barcodeFormats;
            return detector;
        }
    }

    public boolean isOperational() {
        return false;
    }

    public SparseArray<Barcode> detect(com.tech.ayugram.vision.stub.Frame frame) {
        return new SparseArray<>();
    }

    public void release() {}
}