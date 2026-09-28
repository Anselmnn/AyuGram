package com.google.android.gms.tasks;

import com.tech.ayugram.play.stub.Task;

public class Tasks {
    public static <TResult> Task<TResult> forResult(TResult result) {
        return Task.forResult(result);
    }

    public static <TResult> Task<TResult> forException(Exception e) {
        Task<TResult> task = new Task<>();
        return task;
    }

    public static <TResult> Task<TResult> forCanceled() {
        Task<TResult> task = new Task<>();
        return task;
    }
}