package com.tech.ayugram.vision.stub;

import android.content.Context;
import android.util.SparseArray;

/**
 * Phase 2: Stub for com.google.android.gms.vision.barcode.BarcodeDetector
 * Google Vision dependency removed in Phase 2
 */
public class BarcodeDetector {
    private Context context;
    private int barcodeFormats;

    public BarcodeDetector(Context context) {
        this.context = context;
    }

    public BarcodeDetector setBarcodeFormats(int formats) {
        this.barcodeFormats = formats;
        return this;
    }

    public boolean isOperational() {
        return false;
    }

    public SparseArray<Barcode> detect(Frame frame) {
        return new SparseArray<>();
    }

    public void release() {
        // No-op
    }
}