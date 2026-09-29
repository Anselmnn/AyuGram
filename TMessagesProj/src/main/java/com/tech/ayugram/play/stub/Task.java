package com.tech.ayugram.play.stub;

import android.app.Activity;

public class Task<T> {
    private Task() {}

    public interface OnSuccessListener<T> {
        void onSuccess(T result);
    }

    public interface OnFailureListener {
        void onFailure(Exception e);
    }

    public interface OnCompleteListener<T> {
        void onComplete(Task<T> task);
    }

    public interface OnCanceledListener {
        void onCanceled();
    }

    public Task<T> addOnSuccessListener(OnSuccessListener<T> listener) { return this; }
    public Task<T> addOnSuccessListener(Activity activity, OnSuccessListener<T> listener) { return this; }
    public Task<T> addOnFailureListener(OnFailureListener listener) { return this; }
    public Task<T> addOnFailureListener(Activity activity, OnFailureListener listener) { return this; }
    public Task<T> addOnCompleteListener(OnCompleteListener<T> listener) { return this; }
    public Task<T> addOnCompleteListener(Activity activity, OnCompleteListener<T> listener) { return this; }
    public Task<T> addOnCanceledListener(OnCanceledListener listener) { return this; }
    public Task<T> addOnCanceledListener(Activity activity, OnCanceledListener listener) { return this; }

    public boolean isSuccessful() { return false; }
    public boolean isComplete() { return false; }
    public boolean isCanceled() { return false; }
    public T getResult() { return null; }
    public <X extends Throwable> T getResult(Class<X> exceptionClass) throws X { return null; }
    public Exception getException() { return null; }

    public static <T> Task<T> forResult(T result) {
        return new Task<>();
    }

    public static <T> Task<T> forException(Exception e) {
        return new Task<>();
    }

    public static <T> Task<T> forCanceled() {
        return new Task<>();
    }

    public static <T> Task<T> whenAll(java.util.Collection<? extends Task<?>> tasks) {
        return new Task<>();
    }

    public static <T> Task<T> whenAllSuccess(java.util.Collection<? extends Task<?>> tasks) {
        return new Task<>();
    }
}