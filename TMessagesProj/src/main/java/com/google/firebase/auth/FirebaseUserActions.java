package com.google.firebase.auth;

import android.app.Activity;
import com.google.android.gms.tasks.Task;

public class FirebaseUserActions {
    public static FirebaseUserActions getInstance(Activity activity) {
        return new FirebaseUserActions();
    }

    public Task<Void> end(Action action) {
        return Tasks.forResult(null);
    }

    public Task<Void> start(Action action) {
        return Tasks.forResult(null);
    }
}