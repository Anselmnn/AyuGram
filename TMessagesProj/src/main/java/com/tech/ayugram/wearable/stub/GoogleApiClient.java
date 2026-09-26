package com.tech.ayugram.wearable.stub;

/**
 * Phase 2: Stub for com.google.android.gms.common.api.GoogleApiClient
 * Wearable dependency removed in Phase 2
 */
public class GoogleApiClient {
    public interface ConnectionCallbacks {
        void onConnected(android.os.Bundle bundle);
        void onConnectionSuspended(int cause);
    }

    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult result);
    }

    public void connect() {}
    public void disconnect() {}
    public boolean isConnected() { return false; }
    public boolean isConnecting() { return false; }
    public void registerConnectionCallbacks(ConnectionCallbacks listener) {}
    public void unregisterConnectionCallbacks(ConnectionCallbacks listener) {}
    public void registerConnectionFailedListener(OnConnectionFailedListener listener) {}
    public void unregisterConnectionFailedListener(OnConnectionFailedListener listener) {}

    public static class Builder {
        public Builder addConnectionCallbacks(ConnectionCallbacks listener) { return this; }
        public Builder addOnConnectionFailedListener(OnConnectionFailedListener listener) { return this; }
        public Builder addApi(Object api) { return this; }
        public Builder addScope(Object scope) { return this; }
        public GoogleApiClient build() { return new GoogleApiClient(); }
    }

    public static class ConnectionResult {
        public boolean isSuccess() { return false; }
        public int getErrorCode() { return 0; }
    }
}