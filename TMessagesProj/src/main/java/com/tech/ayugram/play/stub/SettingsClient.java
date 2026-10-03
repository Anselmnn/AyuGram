package com.tech.ayugram.play.stub;

import android.content.Context;
import com.google.android.gms.tasks.Task;

public class SettingsClient {
    public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) {
        return com.google.android.gms.tasks.Tasks.forResult(null);
    }

    public static class LocationSettingsRequest {
        public static class Builder {
            public Builder addLocationRequest(android.location.LocationRequest request) { return this; }
            public LocationSettingsRequest build() { return new LocationSettingsRequest(); }
        }
    }

    public static class LocationSettingsResponse {
    }
    
    public static class LocationSettingsRequest {
    }
}