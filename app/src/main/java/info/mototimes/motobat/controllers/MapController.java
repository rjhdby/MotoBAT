package info.mototimes.motobat.controllers;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;

import info.mototimes.motobat.common.Point;
import rx.Observable;

public class MapController {
    private static GoogleMap mMap;

    public static Observable<Boolean> init(SupportMapFragment mapFragment) {
        return Observable.create(subscriber -> {
            mapFragment.getMapAsync(googleMap -> {
                mMap = googleMap;
                subscriber.onNext(true);
                subscriber.onCompleted();
            });
        });

    }

    public static void addMarker(Point point) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(point.getType().drawable);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point.getLatLng())
                .icon(icon)
                .anchor(0.5f, 1);
        mMap.addMarker(markerOptions);
    }
}

