package com.tech.ayugram.cast.stub;

public class MediaSeekOptions {
    private long position;
    private int resumeState;

    public MediaSeekOptions() {}

    public long getPosition() { return position; }
    public int getResumeState() { return resumeState; }
}