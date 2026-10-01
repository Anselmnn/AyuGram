package com.tech.ayugram.play.stub;

import android.content.Context;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

public class GoogleMapsProvider {
    private GoogleMap map;
    private boolean mapReady = false;
    private OnMapReadyCallback readyCallback;

    public interface OnMapReadyCallback {
        void onMapReady(GoogleMap googleMap);
    }

    public GoogleMapsProvider(Context context) {
    }

    public GoogleMapsProvider(Context context, AttributeSet attrs) {
    }

    public GoogleMapsProvider(Context context, AttributeSet attrs, int defStyle) {
    }

    public void getMapAsync(OnMapReadyCallback callback) {
        this.readyCallback = callback;
        if (mapReady && map != null) {
            callback.onMapReady(map);
        }
    }

    public GoogleMap getMap() {
        return map;
    }

    public void onCreate(android.os.Bundle savedInstanceState) {
    }

    public void onResume() {
    }

    public void onPause() {
    }

    public void onDestroy() {
    }

    public void onSaveInstanceState(android.os.Bundle outState) {
    }

    public void onLowMemory() {
    }

    public void setMap(GoogleMap googleMap) {
        this.map = googleMap;
        this.mapReady = true;
        if (readyCallback != null) {
            readyCallback.onMapReady(map);
        }
    }

    public static class MapView extends View {
        private GoogleMapsProvider provider;

        public MapView(Context context) {
            super(context);
            provider = new GoogleMapsProvider(context);
        }

        public MapView(Context context, AttributeSet attrs) {
            super(context, attrs);
            provider = new GoogleMapsProvider(context, attrs);
        }

        public MapView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            provider = new GoogleMapsProvider(context, attrs, defStyle);
        }

        public void getMapAsync(OnMapReadyCallback callback) {
            provider.getMapAsync(callback);
        }

        public GoogleMap getMap() {
            return provider.getMap();
        }

        public void onCreate(android.os.Bundle savedInstanceState) {
            provider.onCreate(savedInstanceState);
        }

        public void onResume() {
            provider.onResume();
        }

        public void onPause() {
            provider.onPause();
        }

        public void onDestroy() {
            provider.onDestroy();
        }

        public void onSaveInstanceState(android.os.Bundle outState) {
            provider.onSaveInstanceState(outState);
        }

        public void onLowMemory() {
            provider.onLowMemory();
        }
    }

    public static class SupportMapFragment extends android.app.Fragment {
        private GoogleMapsProvider provider;
        private OnMapReadyCallback callback;

        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            provider = new GoogleMapsProvider(context);
        }

        @Override
        public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, android.os.Bundle savedInstanceState) {
            provider.onCreate(savedInstanceState);
            return new View(getActivity());
        }

        @Override
        public void onResume() {
            super.onResume();
            provider.onResume();
        }

        @Override
        public void onPause() {
            super.onPause();
            provider.onPause();
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            provider.onDestroy();
        }

        @Override
        public void onSaveInstanceState(android.os.Bundle outState) {
            super.onSaveInstanceState(outState);
            provider.onSaveInstanceState(outState);
        }

        @Override
        public void onLowMemory() {
            super.onLowMemory();
            provider.onLowMemory();
        }

        public void getMapAsync(OnMapReadyCallback callback) {
            this.callback = callback;
            provider.getMapAsync(googleMap -> {
                if (callback != null) {
                    callback.onMapReady(googleMap);
                }
            });
        }
    }

    public static final class MapsInitializer {
        public static void initialize(Context context) {
        }

        public static void initialize(Context context, Runnable onSuccess, Runnable onFailure) {
            if (onSuccess != null) {
                if (Looper.myLooper() == Looper.getMainLooper()) {
                    onSuccess.run();
                } else {
                    new android.os.Handler(Looper.getMainLooper()).post(onSuccess);
                }
            }
        }

        public static boolean isGooglePlayServicesAvailable(Context context) {
            return true;
        }

        public static final int SUCCESS = 0;
        public static final int SERVICE_MISSING = 1;
        public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
        public static final int SERVICE_DISABLED = 3;
        public static final int SERVICE_INVALID = 9;
    }
}