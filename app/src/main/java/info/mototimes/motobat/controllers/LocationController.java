package info.mototimes.motobat.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.android.gms.maps.model.CameraPosition;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

public class LocationController {
    private static ReactiveLocationProvider locationProvider;
    private static Context                  context;

    public static boolean isInitialized() {
        return locationProvider != null;
    }

    public static Observable<Boolean> init(Context context) {
        LocationController.context = context;
        locationProvider = new ReactiveLocationProvider(context);
        return Observable.just(true);
    }

//    public static CameraPosition initialCameraPosition() {
//        SharedPreferences pm  = PreferenceManager.getDefaultSharedPreferences(context);
//        double            lat = pm.getFloat("lat", 55.752295f);
//        float             lon = pm.getFloat("lon", 37.622735f);
//    }
}
