package com.tech.ayugram.play.stub;

public final class GoogleMapOptions {
    private int mapType = GoogleMap.MAP_TYPE_NORMAL;
    private CameraPosition camera;
    private boolean compassEnabled = true;
    private boolean zoomControlsEnabled = false;
    private boolean scrollGesturesEnabled = true;
    private boolean zoomGesturesEnabled = true;
    private boolean tiltGesturesEnabled = true;
    private boolean rotateGesturesEnabled = true;
    private boolean mapToolbarEnabled = true;
    private boolean ambientEnabled = false;
    private boolean liteMode = false;
    private boolean zOrderOnTop = false;
    private boolean useViewLifecycleInFragment = false;

    public GoogleMapOptions mapType(int mapType) {
        this.mapType = mapType;
        return this;
    }

    public int getMapType() {
        return mapType;
    }

    public GoogleMapOptions camera(CameraPosition camera) {
        this.camera = camera;
        return this;
    }

    public CameraPosition getCamera() {
        return camera;
    }

    public GoogleMapOptions compassEnabled(boolean enabled) {
        this.compassEnabled = enabled;
        return this;
    }

    public boolean getCompassEnabled() {
        return compassEnabled;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean enabled) {
        this.zoomControlsEnabled = enabled;
        return this;
    }

    public boolean getZoomControlsEnabled() {
        return zoomControlsEnabled;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean enabled) {
        this.scrollGesturesEnabled = enabled;
        return this;
    }

    public boolean getScrollGesturesEnabled() {
        return scrollGesturesEnabled;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean enabled) {
        this.zoomGesturesEnabled = enabled;
        return this;
    }

    public boolean getZoomGesturesEnabled() {
        return zoomGesturesEnabled;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean enabled) {
        this.tiltGesturesEnabled = enabled;
        return this;
    }

    public boolean getTiltGesturesEnabled() {
        return tiltGesturesEnabled;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean enabled) {
        this.rotateGesturesEnabled = enabled;
        return this;
    }

    public boolean getRotateGesturesEnabled() {
        return rotateGesturesEnabled;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean enabled) {
        this.mapToolbarEnabled = enabled;
        return this;
    }

    public boolean getMapToolbarEnabled() {
        return mapToolbarEnabled;
    }

    public GoogleMapOptions ambientEnabled(boolean enabled) {
        this.ambientEnabled = enabled;
        return this;
    }

    public boolean getAmbientEnabled() {
        return ambientEnabled;
    }

    public GoogleMapOptions liteMode(boolean enabled) {
        this.liteMode = enabled;
        return this;
    }

    public boolean getLiteMode() {
        return liteMode;
    }

    public GoogleMapOptions zOrderOnTop(boolean enabled) {
        this.zOrderOnTop = enabled;
        return this;
    }

    public boolean getZOrderOnTop() {
        return zOrderOnTop;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean enabled) {
        this.useViewLifecycleInFragment = enabled;
        return this;
    }

    public boolean getUseViewLifecycleInFragment() {
        return useViewLifecycleInFragment;
    }
}