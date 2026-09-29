package com.tech.ayugram.cast.stub;

public class MediaError {
    public static final int UNKNOWN_ERROR = 0;
    public static final int INVALID_REQUEST = 1;
    public static final int SESSION_ERROR = 2;
    public static final int NETWORK_ERROR = 3;
    public static final int CANCELED = 4;
    public static final int TIMEOUT = 5;

    public static class DetailedErrorCode {
        public static final int MEDIA_SRC_NOT_SUPPORTED = 100;
        public static final int MEDIA_DECODE = 101;
        public static final int MEDIA_FORMAT = 102;
        public static final int DRM_ERROR = 103;
    }

    private int code;
    private String message;

    public MediaError(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public MediaError() {}

    public int getCode() { return code; }
    public String getMessage() { return message; }
    public int getDetailedErrorCode() { return 0; }
    public int getRequestId() { return 0; }
}