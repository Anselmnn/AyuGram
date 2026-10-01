package com.google.android.gms.common.api;

public class Status {
    public static final int SUCCESS = 0;
    public static final int CANCELED = 1;
    public static final int INTERRUPTED = 2;
    public static final int INTERNAL_ERROR = 3;
    public static final int INVALID_ACCOUNT = 4;
    public static final int RESOLUTION_REQUIRED = 5;
    public static final int NETWORK_ERROR = 6;
    public static final int TIMEOUT = 7;
    public static final int SERVICE_VERSION_UPDATE_REQUIRED = 8;
    public static final int SERVICE_DISABLED = 9;
    public static final int SERVICE_INVALID = 10;
    public static final int DEVELOPER_ERROR = 11;
    public static final int LICENSE_CHECK_FAILED = 12;
    public static final int ERROR = 13;
    public static final int INTERRUPTED_BY_SIGNAL = 14;
    public static final int API_NOT_CONNECTED = 15;
    public static final int DEAD_CLIENT = 16;
    public static final int DEAD_SERVICE = 17;

    private int statusCode;
    private String statusMessage;
    private android.app.PendingIntent resolution;

    public Status(int statusCode) {
        this.statusCode = statusCode;
    }

    public Status(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Status(int statusCode, String statusMessage, android.app.PendingIntent resolution) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.resolution = resolution;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public android.app.PendingIntent getResolution() {
        return resolution;
    }

    public boolean hasResolution() {
        return resolution != null;
    }

    public boolean isSuccess() {
        return statusCode == SUCCESS;
    }

    public boolean isCanceled() {
        return statusCode == CANCELED;
    }

    public boolean isInterrupted() {
        return statusCode == INTERRUPTED;
    }

    @Override
    public String toString() {
        return "Status{statusCode=" + statusCode + ", statusMessage='" + statusMessage + "'}";
    }
}