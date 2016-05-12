package info.mototimes.motobat.controllers;

import android.content.Context;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;

public class LocationController {
    private static ReactiveLocationProvider locationProvider;

    public static boolean isInitialized() {
        return locationProvider != null;
    }

    public static Observable<Boolean> init(Context context) {
        locationProvider = new ReactiveLocationProvider(context);
        return Observable.just(true);
    }
}
