package com.tech.ayugram.play.stub;

public final class LatLngBounds {
    public final LatLng southwest;
    public final LatLng northeast;

    public LatLngBounds(LatLng southwest, LatLng northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng point) {
        return true;
    }

    public LatLngBounds including(LatLng point) {
        return this;
    }

    public LatLng getCenter() {
        return new LatLng(
            (southwest.latitude + northeast.latitude) / 2,
            (southwest.longitude + northeast.longitude) / 2
        );
    }

    public static final class Builder {
        private LatLng southwest;
        private LatLng northeast;

        public Builder include(LatLng point) {
            if (southwest == null) {
                southwest = point;
                northeast = point;
            } else {
                southwest = new LatLng(
                    Math.min(southwest.latitude, point.latitude),
                    Math.min(southwest.longitude, point.longitude)
                );
                northeast = new LatLng(
                    Math.max(northeast.latitude, point.latitude),
                    Math.max(northeast.longitude, point.longitude)
                );
            }
            return this;
        }

        public LatLngBounds build() {
            return new LatLngBounds(southwest, northeast);
        }
    }

    public LatLng getSouthwest() {
        return southwest;
    }

    public LatLng getNortheast() {
        return northeast;
    }
}