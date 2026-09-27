package com.tech.ayugram.vision.stub.face;

import android.content.Context;
import android.util.SparseArray;

public class FaceDetector {
    private Context context;
    private int landmarkType;
    private int classificationType;
    private int mode;
    private boolean trackingEnabled;

    public FaceDetector(Context context) {
        this.context = context;
    }

    public FaceDetector setLandmarkType(int landmarkType) {
        this.landmarkType = landmarkType;
        return this;
    }

    public FaceDetector setClassificationType(int classificationType) {
        this.classificationType = classificationType;
        return this;
    }

    public FaceDetector setMode(int mode) {
        this.mode = mode;
        return this;
    }

    public FaceDetector enableTracking() {
        this.trackingEnabled = true;
        return this;
    }

    public boolean isOperational() {
        return false;
    }

    public SparseArray<Face> detect(com.tech.ayugram.vision.stub.Frame frame) {
        return new SparseArray<>();
    }

    public void release() {}
}