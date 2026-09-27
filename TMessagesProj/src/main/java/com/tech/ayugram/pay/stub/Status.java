package com.tech.ayugram.pay.stub;

import android.content.Intent;

public class Status {
    private int statusCode;
    private String statusMessage;

    public Status() {
        this.statusCode = 0;
    }

    public Status(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isSuccess() {
        return statusCode == 0;
    }

    public boolean isCanceled() {
        return statusCode == 16;
    }

    public boolean isInterrupted() {
        return statusCode == 14;
    }

    public static Status getStatusFromIntent(android.content.Intent intent) {
        return new Status();
    }
}