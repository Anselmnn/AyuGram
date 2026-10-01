package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.common.api.Status;

public class RemoteMediaClient {
    private MediaInfo currentMediaInfo;
    private int playerState;
    private double volumeLevel;
    private boolean isMuted;
    private long currentPosition;
    private long streamDuration;
    private int idleReason;
    private int preloadStatus;

    public RemoteMediaClient() {
        this.playerState = PLAYER_STATE_IDLE;
        this.idleReason = IDLE_REASON_NONE;
    }

    public void load(MediaInfo mediaInfo, boolean autoPlay) {
        this.currentMediaInfo = mediaInfo;
    }

    public void load(MediaInfo mediaInfo, boolean autoPlay, long position) {
        this.currentMediaInfo = mediaInfo;
    }

    public void load(MediaInfo mediaInfo, boolean autoPlay, long position, long[] activeTrackIds) {
        this.currentMediaInfo = mediaInfo;
    }

    public void play() {
        this.playerState = PLAYER_STATE_PLAYING;
    }

    public void pause() {
        this.playerState = PLAYER_STATE_PAUSED;
    }

    public void stop() {
        this.playerState = PLAYER_STATE_IDLE;
    }

    public void seek(long position) {
        this.currentPosition = position;
    }

    public void setVolume(double volume) {
        this.volumeLevel = volume;
    }

    public void setMute(boolean mute) {
        this.isMuted = mute;
    }

    public MediaInfo getMediaInfo() {
        return currentMediaInfo;
    }

    public int getPlayerState() {
        return playerState;
    }

    public double getVolumeLevel() {
        return volumeLevel;
    }

    public boolean isMuted() {
        return isMuted;
    }

    public long getCurrentPosition() {
        return currentPosition;
    }

    public long getStreamDuration() {
        return streamDuration;
    }

    public int getIdleReason() {
        return idleReason;
    }

    public int getPreloadStatus() {
        return preloadStatus;
    }

    public void addProgressListener(ProgressListener listener) {
    }

    public void removeProgressListener(ProgressListener listener) {
    }

    public void addListener(Listener listener) {
    }

    public void removeListener(Listener listener) {
    }

    public interface ProgressListener {
        void onProgressUpdated(long position, long duration);
    }

    public interface Listener {
        void onStatusUpdated();
        void onMetadataUpdated();
        void onPreloadStatusUpdated();
        void onAdBreakStatusUpdated();
    }

    public static final int PLAYER_STATE_IDLE = 0;
    public static final int PLAYER_STATE_PLAYING = 1;
    public static final int PLAYER_STATE_PAUSED = 2;
    public static final int PLAYER_STATE_BUFFERING = 3;

    public static final int IDLE_REASON_NONE = 0;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_ERROR = 4;

    public static final int PRELOAD_STATUS_IDLE = 0;
    public static final int PRELOAD_STATUS_IN_PROGRESS = 1;
    public static final int PRELOAD_STATUS_COMPLETED = 2;
    public static final int PRELOAD_STATUS_FAILED = 3;
}