package com.tech.ayugram.pay.stub;

import android.app.Activity;
import android.content.Intent;

public class AutoResolveHelper {
    public static final int RESULT_ERROR = 0;

    public static void resolveTask(com.tech.ayugram.play.stub.Task<?> task, Activity activity, int requestCode) {}

    public static com.tech.ayugram.pay.stub.Status getStatusFromIntent(Intent data) {
        return new com.tech.ayugram.pay.stub.Status();
    }
}