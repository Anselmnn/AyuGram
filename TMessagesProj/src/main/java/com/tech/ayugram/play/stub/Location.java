package com.tech.ayugram.play.stub;

public final class Location {
    private double latitude;
    private double longitude;
    private double altitude;
    private float accuracy;
    private float bearing;
    private float speed;
    private long time;
    private String provider;

    public Location(String provider) {
        this.provider = provider;
    }

    public Location(Location location) {
        this.latitude = location.latitude;
        this.longitude = location.longitude;
        this.altitude = location.altitude;
        this.accuracy = location.accuracy;
        this.bearing = location.bearing;
        this.speed = location.speed;
        this.time = location.time;
        this.provider = location.provider;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public boolean hasAltitude() {
        return altitude != 0.0;
    }

    public float getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public boolean hasAccuracy() {
        return accuracy != 0.0f;
    }

    public float getBearing() {
        return bearing;
    }

    public void setBearing(float bearing) {
        this.bearing = bearing;
    }

    public boolean hasBearing() {
        return bearing != 0.0f;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public boolean hasSpeed() {
        return speed != 0.0f;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public float distanceTo(Location dest) {
        return 0;
    }

    public void reset() {
        latitude = 0;
        longitude = 0;
        altitude = 0;
        accuracy = 0;
        bearing = 0;
        speed = 0;
        time = 0;
    }

    @Override
    public String toString() {
        return "Location[" + provider + " " + latitude + "," + longitude + "]";
    }
}