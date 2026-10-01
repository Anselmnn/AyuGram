package com.tech.ayugram.play.stub;

public final class UiSettings {
    private boolean zoomControlsEnabled = false;
    private boolean compassEnabled = true;
    private boolean myLocationButtonEnabled = true;
    private boolean scrollGesturesEnabled = true;
    private boolean zoomGesturesEnabled = true;
    private boolean tiltGesturesEnabled = true;
    private boolean rotateGesturesEnabled = true;
    private boolean mapToolbarEnabled = true;
    private boolean indoorLevelPickerEnabled = true;

    public void setZoomControlsEnabled(boolean enabled) {
        this.zoomControlsEnabled = enabled;
    }

    public boolean isZoomControlsEnabled() {
        return zoomControlsEnabled;
    }

    public void setCompassEnabled(boolean enabled) {
        this.compassEnabled = enabled;
    }

    public boolean isCompassEnabled() {
        return compassEnabled;
    }

    public void setMyLocationButtonEnabled(boolean enabled) {
        this.myLocationButtonEnabled = enabled;
    }

    public boolean isMyLocationButtonEnabled() {
        return myLocationButtonEnabled;
    }

    public void setScrollGesturesEnabled(boolean enabled) {
        this.scrollGesturesEnabled = enabled;
    }

    public boolean isScrollGesturesEnabled() {
        return scrollGesturesEnabled;
    }

    public void setZoomGesturesEnabled(boolean enabled) {
        this.zoomGesturesEnabled = enabled;
    }

    public boolean isZoomGesturesEnabled() {
        return zoomGesturesEnabled;
    }

    public void setTiltGesturesEnabled(boolean enabled) {
        this.tiltGesturesEnabled = enabled;
    }

    public boolean isTiltGesturesEnabled() {
        return tiltGesturesEnabled;
    }

    public void setRotateGesturesEnabled(boolean enabled) {
        this.rotateGesturesEnabled = enabled;
    }

    public boolean isRotateGesturesEnabled() {
        return rotateGesturesEnabled;
    }

    public void setMapToolbarEnabled(boolean enabled) {
        this.mapToolbarEnabled = enabled;
    }

    public boolean isMapToolbarEnabled() {
        return mapToolbarEnabled;
    }

    public void setIndoorLevelPickerEnabled(boolean enabled) {
        this.indoorLevelPickerEnabled = enabled;
    }

    public boolean isIndoorLevelPickerEnabled() {
        return indoorLevelPickerEnabled;
    }

    public void setAllGesturesEnabled(boolean enabled) {
        setScrollGesturesEnabled(enabled);
        setZoomGesturesEnabled(enabled);
        setTiltGesturesEnabled(enabled);
        setRotateGesturesEnabled(enabled);
    }
}