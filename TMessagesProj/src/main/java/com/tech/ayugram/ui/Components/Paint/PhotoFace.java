package com.tech.ayugram.ui.Components.Paint;

import android.graphics.Bitmap;
import android.graphics.PointF;

import com.tech.ayugram.ui.Components.Size;

import java.util.List;

/**
 * Phase 2: Stub implementation of PhotoFace (Vision dependency removed)
 */
public class PhotoFace {

    private float width;
    private float angle;

    private PointF foreheadPoint;

    private PointF eyesCenterPoint;
    private float eyesDistance;

    private PointF mouthPoint;
    private PointF chinPoint;

    public PhotoFace(Bitmap sourceBitmap, Size targetSize, boolean sideward) {
        // Phase 2: Vision removed - no face detection
        // Constructor now takes bitmap and target size directly
    }

    /**
     * Phase 2: Stub constructor for compatibility
     */
    @Deprecated
    public PhotoFace(Object face, Bitmap sourceBitmap, Size targetSize, boolean sideward) {
        // Phase 2: Vision removed - no face detection
        // This constructor is kept for compatibility but does nothing
    }

    public boolean isSufficient() {
        // Phase 2: Vision removed - always return false
        return false;
    }

    private void transposePoint() {
        // Phase 2: Vision removed
    }

    public float getWidth() {
        return width;
    }

    public float getAngle() {
        return angle;
    }

    public PointF getForeheadPoint() {
        return foreheadPoint;
    }

    public PointF getEyesCenterPoint() {
        return eyesCenterPoint;
    }

    public float getEyesDistance() {
        return eyesDistance;
    }

    public PointF getMouthPoint() {
        return mouthPoint;
    }

    public PointF getChinPoint() {
        return chinPoint;
    }
}