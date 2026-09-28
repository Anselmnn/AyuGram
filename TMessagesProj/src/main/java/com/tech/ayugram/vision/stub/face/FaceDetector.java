package com.tech.ayugram.vision.stub.face;

import android.content.Context;
import android.util.SparseArray;

public class FaceDetector {
    private Context context;
    private int landmarkType;
    private int classificationType;
    private int mode;
    private boolean trackingEnabled;

    private FaceDetector(Context context) {
        this.context = context;
    }

    public static class Builder {
        private Context context;
        private int landmarkType;
        private int classificationType;
        private int mode;
        private boolean trackingEnabled;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setLandmarkType(int landmarkType) {
            this.landmarkType = landmarkType;
            return this;
        }

        public Builder setClassificationType(int classificationType) {
            this.classificationType = classificationType;
            return this;
        }

        public Builder setMode(int mode) {
            this.mode = mode;
            return this;
        }

        public Builder enableTracking() {
            this.trackingEnabled = true;
            return this;
        }

        public FaceDetector build() {
            FaceDetector detector = new FaceDetector(context);
            detector.landmarkType = this.landmarkType;
            detector.classificationType = this.classificationType;
            detector.mode = this.mode;
            detector.trackingEnabled = this.trackingEnabled;
            return detector;
        }
    }

    public boolean isOperational() {
        return false;
    }

    public SparseArray<Face> detect(com.tech.ayugram.vision.stub.Frame frame) {
        return new SparseArray<>();
    }

    public void release() {}
}