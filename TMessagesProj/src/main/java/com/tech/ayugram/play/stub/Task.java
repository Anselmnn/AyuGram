package com.tech.ayugram.play.stub;

public abstract class Task<TResult> {
    public abstract TResult getResult();

    public static <TResult> Task<TResult> forResult(TResult result) {
        return new Task<TResult>() {
            @Override
            public TResult getResult() {
                return result;
            }
        };
    }
}