package com.tech.ayugram.play.stub;

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