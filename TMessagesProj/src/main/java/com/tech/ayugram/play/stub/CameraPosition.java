package com.tech.ayugram.play.stub;

public class CameraPosition {
    public final double targetLat;
    public final double targetLng;
    public final float zoom;
    public final float bearing;
    public final float tilt;

    private CameraPosition(Builder builder) {
        this.targetLat = builder.targetLat;
        this.targetLng = builder.targetLng;
        this.zoom = builder.zoom;
        this.bearing = builder.bearing;
        this.tilt = builder.tilt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private double targetLat;
        private double targetLng;
        private float zoom;
        private float bearing;
        private float tilt;

        public Builder target(double lat, double lng) {
            this.targetLat = lat;
            this.targetLng = lng;
            return this;
        }

        public Builder zoom(float zoom) {
            this.zoom = zoom;
            return this;
        }

        public Builder bearing(float bearing) {
            this.bearing = bearing;
            return this;
        }

        public Builder tilt(float tilt) {
            this.tilt = tilt;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(this);
        }
    }
}