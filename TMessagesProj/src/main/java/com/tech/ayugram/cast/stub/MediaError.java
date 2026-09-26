package com.tech.ayugram.cast.stub;

/**
 * Phase 2: Stub for com.google.android.gms.cast.MediaError
 * Cast dependency removed in Phase 2
 */
public class MediaError {
    public static final int UNKNOWN_ERROR = 0;
    public static final int INVALID_REQUEST = 1;
    public static final int SESSION_ERROR = 2;
    public static final int NETWORK_ERROR = 3;
    public static final int CANCELED = 4;
    public static final int TIMEOUT = 5;

    private int code;
    private String message;

    public MediaError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
}