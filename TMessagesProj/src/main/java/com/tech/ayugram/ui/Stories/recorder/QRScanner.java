package com.tech.ayugram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;

import androidx.annotation.NonNull;

import com.tech.ayugram.messenger.AndroidUtilities;
import com.tech.ayugram.messenger.MessagesController;
import com.tech.ayugram.messenger.SharedConfig;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.messenger.Utilities;
import com.tech.ayugram.messenger.camera.CameraView;
import com.tech.ayugram.ui.Components.AnimatedFloat;
import com.tech.ayugram.ui.Components.CubicBezierInterpolator;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Phase 2: Stub implementation of QRScanner (Vision dependency removed)
 */
public class QRScanner {

    private final AtomicReference<Object> detector = new AtomicReference<>();
    private final AtomicBoolean paused = new AtomicBoolean(false);

    private final Utilities.Callback<Detected> listener;
    private Detected lastDetected;
    private final String prefix;

    public QRScanner(Context context, Utilities.Callback<Detected> whenScanned) {
        this.listener = whenScanned;
        this.prefix = MessagesController.getInstance(UserConfig.selectedAccount).linkPrefix;
        // Phase 2: Vision removed - no QR scanning
        // Utilities.globalQueue.postRunnable(() -> {
        //     detector.set(new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.QR_CODE).build());
        //     attach(cameraView);
        // });
    }

    public Detected getDetected() {
        return lastDetected;
    }

    private CameraView cameraView;

    public void destroy() {
        this.cameraView = null;
        Utilities.globalQueue.cancelRunnable(this.process);
    }

    public void attach(CameraView cameraView) {
        this.cameraView = cameraView;
        // Phase 2: Vision removed
    }

    public void setPaused(boolean pause) {
        if (this.paused.getAndSet(pause) == pause) return;

        if (pause) {
            Utilities.globalQueue.cancelRunnable(this.process);
            if (lastDetected != null) {
                lastDetected = null;
                AndroidUtilities.runOnUIThread(() -> QRScanner.this.listener.run(null));
            }
        } else {
            Utilities.globalQueue.cancelRunnable(this.process);
            // Phase 2: Vision removed
        }
    }

    public boolean isPaused() {
        return this.paused.get();
    }

    private Bitmap cacheBitmap;
    private final Runnable process = () -> {
        // Phase 2: Vision removed - no QR scanning
    };

    private Detected detect(Bitmap bitmap) {
        // Phase 2: Vision removed - no QR scanning
        return null;
    }

    public static class Detected {
        public final String link;
        public final PointF[] cornerPoints;

        public Detected(String link, PointF[] cornerPoints) {
            this.link = link;
            this.cornerPoints = cornerPoints;
        }
    }

    /**
     * Phase 2: Stub for QR region drawer (Vision dependency removed)
     */
    public static class QrRegionDrawer {
        private final Runnable onInvalidate;

        public QrRegionDrawer(Runnable onInvalidate) {
            this.onInvalidate = onInvalidate;
        }

        public void draw(android.graphics.Canvas canvas) {
            // Phase 2: Vision removed - no QR region drawing
        }

        public void setRect(android.graphics.RectF rect) {
            // Phase 2: Vision removed
        }
    }
}