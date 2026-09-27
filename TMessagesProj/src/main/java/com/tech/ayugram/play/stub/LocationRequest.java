package com.tech.ayugram.play.stub;

public class LocationRequest {
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;

    private int priority;
    private long interval;
    private long fastestInterval;
    private long expireTime;
    private int numUpdates;

    public LocationRequest() {}

    public LocationRequest setPriority(int priority) { this.priority = priority; return this; }
    public LocationRequest setInterval(long interval) { this.interval = interval; return this; }
    public LocationRequest setFastestInterval(long fastestInterval) { this.fastestInterval = fastestInterval; return this; }
    public LocationRequest setExpirationDuration(long duration) { this.expireTime = System.currentTimeMillis() + duration; return this; }
    public LocationRequest setNumUpdates(int numUpdates) { this.numUpdates = numUpdates; return this; }

    public int getPriority() { return priority; }
    public long getInterval() { return interval; }
    public long getFastestInterval() { return fastestInterval; }
    public long getExpirationTime() { return expireTime; }
    public int getNumUpdates() { return numUpdates; }
}