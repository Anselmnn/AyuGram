package com.tech.ayugram.messenger.maps;

import android.content.Context;

import androidx.annotation.Keep;

import com.google.android.gms.common.GoogleApiAvailability;
import com.tech.ayugram.ApplicationLoader;
import com.tech.ayugram.messenger.UserConfig;
import com.tech.ayugram.play.stub.GoogleMap;
import com.tech.ayugram.play.stub.LatLng;
import com.tech.ayugram.play.stub.MarkerOptions;

@Keep
public class HybridMapsManager {
    private static HybridMapsManager instance;
    private final Context context;
    private final UserConfig userConfig;
    
    public enum MapProvider {
        GOOGLE_MAPS,      // Google Maps via play-services-maps (requires GMS)
        MAPLIBRE_OSM      // MapLibre + OpenStreetMap (no GMS required)
    }
    
    private MapProvider currentProvider = MapProvider.GOOGLE_MAPS;
    private Object mapInstance; // GoogleMap or MapLibre MapView

    private HybridMapsManager() {
        context = ApplicationLoader.getApplicationContext();
        userConfig = UserConfig.getInstance();
        detectBestProvider();
    }

    public static synchronized HybridMapsManager getInstance() {
        if (instance == null) {
            instance = new HybridMapsManager();
        }
        return instance;
    }

    /**
     * R17: Hybrid maps - GMS → Google Maps, else MapLibre + OSM
     * Auto-detect on startup, allow manual override in settings
     */
    private void detectBestProvider() {
        // Check if Google Play Services is available
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int result = apiAvailability.isGooglePlayServicesAvailable(context);
        
        if (result == GoogleApiAvailability.SUCCESS) {
            // Check if Maps API is available
            if (isGoogleMapsApiAvailable()) {
                currentProvider = MapProvider.GOOGLE_MAPS;
            } else {
                currentProvider = MapProvider.MAPLIBRE_OSM;
            }
        } else {
            currentProvider = MapProvider.MAPLIBRE_OSM;
        }
        
        // Check user preference override
        String preference = getMapProviderPreference();
        if (preference != null) {
            try {
                currentProvider = MapProvider.valueOf(preference);
            } catch (Exception e) {
                // Ignore, use detected
            }
        }
    }

    private boolean isGoogleMapsApiAvailable() {
        // Additional check for Maps API specifically
        try {
            Class.forName("com.google.android.gms.maps.GoogleMap");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public MapProvider getCurrentProvider() {
        return currentProvider;
    }

    public void setProvider(MapProvider provider) {
        currentProvider = provider;
        saveMapProviderPreference(provider.name());
    }

    public void initializeMap(Object mapView) {
        this.mapInstance = mapView;
        
        if (currentProvider == MapProvider.GOOGLE_MAPS) {
            initializeGoogleMaps((com.tech.ayugram.play.stub.GoogleMap) mapView);
        } else {
            initializeMapLibre(mapView);
        }
    }

    private void initializeGoogleMaps(GoogleMap googleMap) {
        // Configure Google Maps
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        
        // Enable location if permission granted
        // googleMap.setMyLocationEnabled(hasLocationPermission());
    }

    private void initializeMapLibre(Object mapView) {
        // MapLibre initialization would go here
        // org.maplibre.android.MapView mapLibreView = (org.maplibre.android.MapView) mapView;
        // mapLibreView.setStyleUrl("https://maps.libre.io/styles/osm-bright/style.json");
    }

    // Google Maps API wrappers
    public void addMarker(LatLng position, String title, String snippet) {
        if (currentProvider == MapProvider.GOOGLE_MAPS && mapInstance instanceof GoogleMap) {
            GoogleMap googleMap = (GoogleMap) mapInstance;
            MarkerOptions options = new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet);
            googleMap.addMarker(options);
        } else {
            // MapLibre marker
        }
    }

    public void moveCamera(LatLng target, float zoom) {
        if (currentProvider == MapProvider.GOOGLE_MAPS && mapInstance instanceof GoogleMap) {
            GoogleMap googleMap = (GoogleMap) mapInstance;
            com.tech.ayugram.play.stub.CameraUpdate update = 
                com.tech.ayugram.play.stub.CameraUpdateFactory.newLatLngZoom(target, zoom);
            googleMap.moveCamera(update);
        } else {
            // MapLibre camera
        }
    }

    public void animateCamera(LatLng target, float zoom, int durationMs) {
        if (currentProvider == MapProvider.GOOGLE_MAPS && mapInstance instanceof GoogleMap) {
            GoogleMap googleMap = (GoogleMap) mapInstance;
            com.tech.ayugram.play.stub.CameraUpdate update = 
                com.tech.ayugram.play.stub.CameraUpdateFactory.newLatLngZoom(target, zoom);
            googleMap.animateCamera(update, durationMs, null);
        } else {
            // MapLibre camera animation
        }
    }

    // Static map URLs (for chat previews, etc.)
    public String getStaticMapUrl(LatLng center, int zoom, int width, int height) {
        if (currentProvider == MapProvider.GOOGLE_MAPS) {
            // Google Static Maps API
            return "https://maps.googleapis.com/maps/api/staticmap?" +
                   "center=" + center.latitude + "," + center.longitude +
                   "&zoom=" + zoom +
                   "&size=" + width + "x" + height +
                   "&markers=color:red%7C" + center.latitude + "," + center.longitude +
                   "&key=" + getGoogleMapsApiKey();
        } else {
            // MapLibre/OSM static tiles (using static-maps.yandex.ru as fallback per master)
            return "https://static-maps.yandex.ru/1.x/?" +
                   "ll=" + center.longitude + "," + center.latitude +
                   "&z=" + zoom +
                   "&size=" + width + "," + height +
                   "&l=map" +
                   "&pt=" + center.longitude + "," + center.latitude + ",pm2rdm";
        }
    }

    private String getGoogleMapsApiKey() {
        // In production, get from secure config
        return "YOUR_GOOGLE_MAPS_API_KEY";
    }

    // Location permissions
    public boolean hasLocationPermission() {
        // Check ACCESS_FINE_LOCATION / ACCESS_COARSE_LOCATION
        return true; // Simplified
    }

    // User preferences
    private String getMapProviderPreference() {
        return ApplicationLoader.getPreferences().getString("map_provider", null);
    }

    private void saveMapProviderPreference(String provider) {
        ApplicationLoader.getPreferences().edit()
            .putString("map_provider", provider)
            .apply();
    }

    // Geocoding
    public void geocodeAddress(String address, GeocodeCallback callback) {
        if (currentProvider == MapProvider.GOOGLE_MAPS) {
            // Google Geocoding API
            new Thread(() -> {
                try {
                    String url = "https://maps.googleapis.com/maps/api/geocode/json?" +
                                 "address=" + java.net.URLEncoder.encode(address, "UTF-8") +
                                 "&key=" + getGoogleMapsApiKey();
                    // Parse response and call callback
                    callback.onResult(null, null);
                } catch (Exception e) {
                    callback.onError(e.getMessage());
                }
            }).start();
        } else {
            // Nominatim (OSM)
            new Thread(() -> {
                try {
                    String url = "https://nominatim.openstreetmap.org/search?" +
                                 "q=" + java.net.URLEncoder.encode(address, "UTF-8") +
                                 "&format=json&limit=1";
                    // Parse response and call callback
                    callback.onResult(null, null);
                } catch (Exception e) {
                    callback.onError(e.getMessage());
                }
            }).start();
        }
    }

    public void reverseGeocode(LatLng location, GeocodeCallback callback) {
        if (currentProvider == MapProvider.GOOGLE_MAPS) {
            // Google Reverse Geocoding
        } else {
            // Nominatim Reverse Geocoding
        }
    }

    public interface GeocodeCallback {
        void onResult(LatLng location, String address);
        void onError(String error);
    }
}