package com.tech.ayugram.maps.stub;

import com.tech.ayugram.maps.stub.model.LatLng;

public class LatLngBounds {
    public final LatLng northeast;
    public final LatLng southwest;

    public LatLngBounds(LatLng southwest, LatLng northeast) {
        this.southwest = southwest;
        this.northeast = northeast;
    }

    public static class Builder {
        private LatLng southwest;
        private LatLng northeast;

        public Builder include(LatLng latLng) {
            if (southwest == null) {
                southwest = northeast = latLng;
            } else {
                if (latLng.latitude < southwest.latitude) southwest = new LatLng(latLng.latitude, southwest.longitude);
                if (latLng.longitude < southwest.longitude) southwest = new LatLng(southwest.latitude, latLng.longitude);
                if (latLng.latitude > northeast.latitude) northeast = new LatLng(latLng.latitude, northeast.longitude);
                if (latLng.longitude > northeast.longitude) northeast = new LatLng(northeast.latitude, latLng.longitude);
            }
            return this;
        }

        public LatLngBounds build() {
            return new LatLngBounds(southwest, northeast);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public LatLng getCenter() {
        return new LatLng(
            (northeast.latitude + southwest.latitude) / 2,
            (northeast.longitude + southwest.longitude) / 2
        );
    }

    public boolean contains(LatLng latLng) {
        return latLng.latitude >= southwest.latitude && latLng.latitude <= northeast.latitude &&
               latLng.longitude >= southwest.longitude && latLng.longitude <= northeast.longitude;
    }
}