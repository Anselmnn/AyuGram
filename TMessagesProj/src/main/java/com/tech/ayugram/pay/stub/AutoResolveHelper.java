package com.tech.ayugram.pay.stub;

import android.app.Activity;
import android.content.Intent;

/**
 * Phase 2: Stub for com.google.android.gms.wallet.AutoResolveHelper
 * Google Pay dependency removed in Phase 2
 */
public class AutoResolveHelper {
    public static final int RESULT_ERROR = 0;

    public static void resolveTask(com.tech.ayugram.play.stub.Task<?> task, Activity activity, int requestCode) {
        // No-op
    }

    public static int getStatusFromIntent(Intent data) {
        return 0;
    }
}