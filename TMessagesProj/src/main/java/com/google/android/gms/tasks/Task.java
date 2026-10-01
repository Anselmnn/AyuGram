package com.google.android.gms.tasks;

import com.tech.ayugram.play.stub.Task;

public class Task<TResult> {
    public Task() {}

    public <TContinuationResult> Task<TContinuationResult> addOnSuccessListener(com.google.android.gms.tasks.OnSuccessListener<? super TResult> listener) {
        return null;
    }

    public <TContinuationResult> Task<TContinuationResult> addOnFailureListener(com.google.android.gms.tasks.OnFailureListener listener) {
        return null;
    }

    public <TContinuationResult> Task<TContinuationResult> addOnCompleteListener(com.google.android.gms.tasks.OnCompleteListener<TResult> listener) {
        return null;
    }

    public Task<TResult> continueWith(com.google.android.gms.tasks.Continuation<TResult, TResult> continuation) {
        return null;
    }

    public Task<TResult> continueWithTask(com.google.android.gms.tasks.Continuation<TResult, Task<TResult>> continuation) {
        return null;
    }

    public boolean isSuccessful() { return false; }
    public boolean isCanceled() { return false; }
    public boolean isComplete() { return false; }
    public Exception getException() { return null; }
    public TResult getResult() { return null; }
}