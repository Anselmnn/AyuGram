package com.tech.ayugram.stripe.stub;

public class APIConnectionException extends Exception {
    public APIConnectionException(String message) {
        super(message);
    }

    public APIConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}