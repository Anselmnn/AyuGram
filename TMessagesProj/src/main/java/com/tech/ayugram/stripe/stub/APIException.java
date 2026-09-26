package com.tech.ayugram.stripe.stub;

/**
 * Phase 2: Stub for com.stripe.android.exception.APIException
 * Stripe dependency removed in Phase 2
 */
public class APIException extends Exception {
    private int statusCode;
    private String stripeErrorCode;
    private String requestId;

    public APIException(String message) {
        super(message);
    }

    public APIException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIException(String message, int statusCode, String stripeErrorCode, String requestId) {
        super(message);
        this.statusCode = statusCode;
        this.stripeErrorCode = stripeErrorCode;
        this.requestId = requestId;
    }

    public int getStatusCode() { return statusCode; }
    public String getStripeErrorCode() { return stripeErrorCode; }
    public String getRequestId() { return requestId; }
}