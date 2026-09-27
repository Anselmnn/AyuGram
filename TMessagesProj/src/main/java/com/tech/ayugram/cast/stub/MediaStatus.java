package com.tech.ayugram.cast.stub;

public class MediaStatus {
    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PLAYING = 1;
    public static final int PLAYER_STATE_PAUSED = 2;
    public static final int PLAYER_STATE_BUFFERING = 3;

    private int playerState;
    private long streamPosition;
    private long streamDuration;

    public MediaStatus() {}

    public int getPlayerState() { return playerState; }
    public long getStreamPosition() { return streamPosition; }
    public long getStreamDuration() { return streamDuration; }
}