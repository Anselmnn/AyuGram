package com.tech.ayugram.messenger.chromecast;

/**
 * Phase 2: Stub implementation of ChromecastControllerState (Cast dependency removed)
 */
public class ChromecastControllerState {
    public static final int STATE_IDLE = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_CONNECTED = 2;
    public static final int STATE_CASTING = 3;
    public static final int STATE_BUFFERING = 4;
    public static final int STATE_ERROR = 5;

    private int state = STATE_IDLE;
    private String errorMessage;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}