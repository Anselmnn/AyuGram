package com.tech.ayugram.play.stub;

public class LocationSettingsRequest {
    private LocationRequest locationRequest;

    public static class Builder {
        private LocationRequest locationRequest;

        public Builder addLocationRequest(LocationRequest request) {
            this.locationRequest = request;
            return this;
        }

        public LocationSettingsRequest build() {
            LocationSettingsRequest request = new LocationSettingsRequest();
            return request;
        }
    }

    public static Builder builder() { return new Builder(); }
}