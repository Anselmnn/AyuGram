package com.google.android.gms.cast;

public class Status {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;
    public static final int CANCELED = 2;
    public static final int TIMEOUT = 3;
    public static final int INTERRUPTED = 4;

    public boolean isSuccess() {
        return true;
    }
}