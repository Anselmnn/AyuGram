package com.tech.ayugram.cast.stub;

public class CastDevice {
    private String deviceId;
    private String friendlyName;
    private String modelName;
    private String deviceVersion;
    private String ipAddress;
    private int servicePort;
    private int[] capabilities;
    private int status;

    public String getDeviceId() { return deviceId; }
    public String getFriendlyName() { return friendlyName; }
    public String getModelName() { return modelName; }
    public String getDeviceVersion() { return deviceVersion; }
    public String getIpAddress() { return ipAddress; }
    public int getServicePort() { return servicePort; }
    public int[] getCapabilities() { return capabilities; }
    public int getStatus() { return status; }
}