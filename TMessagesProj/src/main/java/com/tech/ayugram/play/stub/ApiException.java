package com.tech.ayugram.play.stub;

/**
 * Phase 2: Stub for com.google.android.gms.common.api.ApiException
 * Google Play Services dependency removed in Phase 2
 */
public class ApiException extends Exception {
    private final int statusCode;

    public ApiException(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}