package com.google.android.gms.cast;

public class CastDevice {
    private String deviceId;
    private String friendlyName;
    private String modelName;
    private String deviceVersion;
    private String ipAddress;
    private int servicePort;
    private int capabilities;

    public CastDevice() {
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    public String getModelName() {
        return modelName;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getServicePort() {
        return servicePort;
    }

    public int getCapabilities() {
        return capabilities;
    }

    public boolean hasIcons() {
        return false;
    }

    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final int CAPABILITY_AUDIO_OUT = 2;
    public static final int CAPABILITY_VIDEO_IN = 4;
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_MULTIZONE_GROUP = 16;
}