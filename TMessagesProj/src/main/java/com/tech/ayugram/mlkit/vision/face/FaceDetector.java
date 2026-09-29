package com.tech.ayugram.mlkit.vision.face;

import android.content.Context;
import android.util.SparseArray;
import com.tech.ayugram.mlkit.vision.common.InputImage;

public class FaceDetector {
    public static final int ACCURATE_MODE = 1;
    public static final int FAST_MODE = 0;
    public static final int ALL_LANDMARKS = 1;
    public static final int NO_LANDMARKS = 0;
    public static final int ALL_CLASSIFICATIONS = 1;
    public static final int NO_CLASSIFICATIONS = 0;

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

        public Builder setTrackingEnabled(boolean enabled) {
            this.trackingEnabled = enabled;
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

    public boolean isOperational() { return false; }

    public SparseArray<Face> detect(InputImage image) {
        return new SparseArray<>();
    }

    public void close() {}
}
