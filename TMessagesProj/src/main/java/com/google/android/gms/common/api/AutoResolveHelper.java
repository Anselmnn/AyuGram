package com.google.android.gms.common.api;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;

public class AutoResolveHelper {
    private AutoResolveHelper() {
    }

    public static void resolveTask(com.google.android.gms.tasks.Task<?> task, Activity activity, int requestCode) {
        // Empty implementation for stub
    }

    public static void resolveTask(com.google.android.gms.tasks.Task<?> task, Activity activity, int requestCode, IntentSender.SendIntentException e) {
        // Empty implementation for stub
    }
}