package com.google.android.gms.tasks;

public class Tasks {
    public static <TResult> Task<TResult> forResult(TResult result) {
        return null;
    }

    public static <TResult> Task<TResult> forException(Exception e) {
        return null;
    }

    public static <TResult> Task<TResult> forCanceled() {
        return null;
    }
}