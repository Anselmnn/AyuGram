package com.tech.ayugram.maps.stub;

/**
 * Phase 2: Stub for com.google.android.gms.maps.model.CameraPosition
 * Google Maps dependency removed in Phase 2
 */
public class CameraPosition {
    public final LatLng target;
    public final float zoom;
    public final float tilt;
    public final float bearing;

    public CameraPosition(LatLng target, float zoom, float tilt, float bearing) {
        this.target = target;
        this.zoom = zoom;
        this.tilt = tilt;
        this.bearing = bearing;
    }

    public static class Builder {
        private LatLng target;
        private float zoom;
        private float tilt;
        private float bearing;

        public Builder target(LatLng target) {
            this.target = target;
            return this;
        }

        public Builder zoom(float zoom) {
            this.zoom = zoom;
            return this;
        }

        public Builder tilt(float tilt) {
            this.tilt = tilt;
            return this;
        }

        public Builder bearing(float bearing) {
            this.bearing = bearing;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(target, zoom, tilt, bearing);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public LatLng getTarget() { return target; }
    public float getZoom() { return zoom; }
    public float getTilt() { return tilt; }
    public float getBearing() { return bearing; }
}