package com.tech.ayugram.play.stub;

import com.tech.ayugram.play.stub.Task;
import com.tech.ayugram.play.stub.LocationSettingsResponse;

public class SettingsClient {
    public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest request) { return Task.forResult(new LocationSettingsResponse()); }
}