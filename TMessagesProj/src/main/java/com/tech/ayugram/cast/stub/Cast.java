package com.tech.ayugram.cast.stub;

/**
 * Phase 2: Stub for com.google.android.gms.cast.Cast
 * Cast dependency removed in Phase 2
 */
public class Cast {
    private Cast() {}

    public static class MediaError {
        private int code;
        private String message;

        public MediaError(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() { return code; }
        public String getMessage() { return message; }
    }

    public static class MediaSeekOptions {
        private long position;
        private int resumeState;

        public MediaSeekOptions() {}

        public long getPosition() { return position; }
        public int getResumeState() { return resumeState; }
    }

    public static class MediaStatus {
        private int playerState;
        private long streamPosition;
        private long streamDuration;

        public MediaStatus() {}

        public int getPlayerState() { return playerState; }
        public long getStreamPosition() { return streamPosition; }
        public long getStreamDuration() { return streamDuration; }
    }
}