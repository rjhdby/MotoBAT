package info.mototimes.motobat.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class PreferencesController {
    private static SharedPreferences preferences;

    public static void init(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static LatLng getLatLng() {
        double lat = (double) preferences.getFloat("lat", 55.752295f);
        double lng = (double) preferences.getFloat("lon", 37.622735f);
        return new LatLng(lat, lng);
    }

    public static float getZoom() {
        return preferences.getFloat("zoom", 10);
    }

    public static float getTilt() {
        return preferences.getFloat("tilt", 0);
    }

    public static float getBearing() {
        return preferences.getFloat("bearing", 0);
    }

    public static CameraPosition getCamera() {
        return new CameraPosition(getLatLng(), getZoom(), getTilt(), getBearing());
    }

    public static void updateCamera(CameraPosition cameraPosition) {
        preferences.edit()
                   .putFloat("lat", (float) cameraPosition.target.latitude)
                   .putFloat("lon", (float) cameraPosition.target.longitude)
                   .putFloat("zoom", cameraPosition.zoom)
                   .putFloat("tilt", cameraPosition.tilt)
                   .putFloat("bearing", cameraPosition.bearing)
                   .apply();
    }
}
