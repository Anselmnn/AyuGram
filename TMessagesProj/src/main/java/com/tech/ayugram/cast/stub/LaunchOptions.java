package com.tech.ayugram.cast.stub;

public class LaunchOptions {
    public boolean relaunchIfRunning;
    public String language;

    public LaunchOptions() {
        this.relaunchIfRunning = false;
        this.language = "en";
    }
}