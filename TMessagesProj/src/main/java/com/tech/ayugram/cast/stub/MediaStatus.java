package com.tech.ayugram.cast.stub;

public class MediaStatus {
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PLAYING = 1;
    public static final int PLAYER_STATE_PAUSED = 2;
    public static final int PLAYER_STATE_BUFFERING = 3;

    public static final int IDLE_REASON_CANCELED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 2;
    public static final int IDLE_REASON_FINISHED = 3;
    public static final int IDLE_REASON_ERROR = 4;

    public static final int REPEAT_MODE_OFF = 0;
    public static final int REPEAT_MODE_ALL = 1;
    public static final int REPEAT_MODE_SINGLE = 2;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;

    private int playerState;
    private long streamPosition;
    private long streamDuration;

    public MediaStatus() {}

    public int getPlayerState() { return playerState; }
    public long getStreamPosition() { return streamPosition; }
    public long getStreamDuration() { return streamDuration; }
    public int getIdleReason() { return 0; }
}