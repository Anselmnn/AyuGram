package com.tech.ayugram.play.stub;

import android.graphics.Point;
import android.view.View;

public interface GoogleMap {
    void moveCamera(CameraUpdate update);
    void animateCamera(CameraUpdate update);
    void animateCamera(CameraUpdate update, CancelableCallback callback);
    void animateCamera(CameraUpdate update, int durationMs, CancelableCallback callback);
    void stopAnimation();
    CameraPosition getCameraPosition();
    float getMaxZoomLevel();
    float getMinZoomLevel();
    Projection getProjection();
    UiSettings getUiSettings();
    void setMapType(int mapType);
    int getMapType();
    void setTrafficEnabled(boolean enabled);
    boolean isTrafficEnabled();
    void setIndoorEnabled(boolean enabled);
    boolean isIndoorEnabled();
    void setBuildingsEnabled(boolean enabled);
    boolean isBuildingsEnabled();
    void setPadding(int left, int top, int right, int bottom);
    void setContentDescription(String contentDescription);
    String getContentDescription();
    Marker addMarker(MarkerOptions options);
    void clear();
    void setOnMarkerClickListener(OnMarkerClickListener listener);
    void setOnInfoWindowClickListener(OnInfoWindowClickListener listener);
    void setOnMapClickListener(OnMapClickListener listener);
    void setOnMapLongClickListener(OnMapLongClickListener listener);
    void setOnCameraChangeListener(OnCameraChangeListener listener);
    void setOnCameraIdleListener(OnCameraIdleListener listener);
    void setOnCameraMoveStartedListener(OnCameraMoveStartedListener listener);
    void setOnCameraMoveListener(OnCameraMoveListener listener);
    void setOnCameraMoveCanceledListener(OnCameraMoveCanceledListener listener);
    void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener listener);
    void setOnMyLocationClickListener(OnMyLocationClickListener listener);
    boolean isMyLocationEnabled();
    void setMyLocationEnabled(boolean enabled);
    Location getMyLocation();
    void setInfoWindowAdapter(InfoWindowAdapter adapter);

    interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    interface OnMapClickListener {
        void onMapClick(LatLng point);
    }

    interface OnMapLongClickListener {
        void onMapLongClick(LatLng point);
    }

    interface OnCameraChangeListener {
        void onCameraChange(CameraPosition position);
    }

    interface OnCameraIdleListener {
        void onCameraIdle();
    }

    interface OnCameraMoveStartedListener {
        void onCameraMoveStarted(int reason);
    }

    interface OnCameraMoveListener {
        void onCameraMove();
    }

    interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    interface OnMyLocationClickListener {
        void onMyLocationClick(Location location);
    }

    interface InfoWindowAdapter {
        View getInfoWindow(Marker marker);
        View getInfoContents(Marker marker);
    }

    interface CancelableCallback {
        void onFinish();
        void onCancel();
    }

    int MAP_TYPE_NONE = 0;
    int MAP_TYPE_NORMAL = 1;
    int MAP_TYPE_SATELLITE = 2;
    int MAP_TYPE_TERRAIN = 3;
    int MAP_TYPE_HYBRID = 4;
}