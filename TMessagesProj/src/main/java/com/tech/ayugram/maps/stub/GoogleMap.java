package com.tech.ayugram.maps.stub;

import com.tech.ayugram.maps.stub.LatLng;
import com.tech.ayugram.maps.stub.Marker;
import com.tech.ayugram.maps.stub.MarkerOptions;
import com.tech.ayugram.maps.stub.Circle;
import com.tech.ayugram.maps.stub.CircleOptions;
import com.tech.ayugram.maps.stub.Polygon;
import com.tech.ayugram.maps.stub.PolygonOptions;
import com.tech.ayugram.maps.stub.Polyline;
import com.tech.ayugram.maps.stub.PolylineOptions;
import com.tech.ayugram.maps.stub.CameraUpdate;

public class GoogleMap {
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    public interface OnMyLocationClickListener {
        void onMyLocationClick(android.location.Location location);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(android.graphics.Bitmap bitmap);
    }

    public void animateCamera(CameraUpdate update) {}
    public void animateCamera(CameraUpdate update, int durationMs, CancelableCallback callback) {}
    public void animateCamera(CameraUpdate update, CancelableCallback callback) {}
    public void moveCamera(CameraUpdate update) {}

    public void setOnMapClickListener(OnMapClickListener listener) {}
    public void setOnMapLongClickListener(OnMapLongClickListener listener) {}
    public void setOnMarkerClickListener(OnMarkerClickListener listener) {}
    public void setOnInfoWindowClickListener(OnInfoWindowClickListener listener) {}
    public void setOnMyLocationButtonClickListener(OnMyLocationButtonClickListener listener) {}
    public void setOnMyLocationClickListener(OnMyLocationClickListener listener) {}
    public void setMapType(int mapType) {}
    public void setTrafficEnabled(boolean enabled) {}
    public void setBuildingsEnabled(boolean enabled) {}
    public void setIndoorEnabled(boolean enabled) {}
    public void setMyLocationEnabled(boolean enabled) {}
    public UiSettings getUiSettings() { return new UiSettings(); }
    public void setOnCameraChangeListener(OnCameraChangeListener listener) {}
    public void setOnCameraMoveListener(OnCameraMoveListener listener) {}
    public void setOnCameraIdleListener(OnCameraIdleListener listener) {}
    public void setOnCameraMoveStartedListener(OnCameraMoveStartedListener listener) {}
    public void setOnCameraMoveCanceledListener(OnCameraMoveCanceledListener listener) {}

    public Marker addMarker(MarkerOptions options) { return new Marker(); }
    public Circle addCircle(CircleOptions options) { return new Circle(); }
    public Polygon addPolygon(PolygonOptions options) { return new Polygon(); }
    public Polyline addPolyline(PolylineOptions options) { return new Polyline(); }

    public void clear() {}
    public void snapshot(SnapshotReadyCallback callback) {}

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition position);
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveStartedListener {
        void onCameraMoveStarted(int reason);
    }

    public interface OnCameraMoveCanceledListener {
        void onCameraMoveCanceled();
    }

    public interface CancelableCallback {
        void onFinish();
        void onCancel();
    }
}