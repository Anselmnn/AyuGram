package com.tech.ayugram.pay.stub;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

public class Status {
    public static Status getStatusFromIntent(Intent data) { return new Status(); }
    public int getStatusCode() { return 0; }
    public String getStatusMessage() { return ""; }
    public boolean isSuccess() { return false; }
    public boolean isCanceled() { return false; }

    public void startResolutionForResult(Activity activity, int requestCode) throws IntentSender.SendIntentException {}
    public void startResolutionForResult(Activity activity, int requestCode, IntentSender intentSender) throws IntentSender.SendIntentException {}
    public void startResolutionForResult(Activity activity, int requestCode, IntentSender intentSender, Bundle bundle) throws IntentSender.SendIntentException {}
}