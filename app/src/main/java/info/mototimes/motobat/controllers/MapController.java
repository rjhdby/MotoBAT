package info.mototimes.motobat.controllers;

import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import info.mototimes.motobat.common.Point;
import info.mototimes.motobat.dictionaries.PointType;
import rx.Observable;

public class MapController {
    private static GoogleMap mMap;

    public static boolean isInitialized() {
        return mMap != null;
    }

    public static Observable<Boolean> init(SupportMapFragment mapFragment) {
        return Observable.create(subscriber -> {
            mapFragment.getMapAsync(googleMap -> {
                mMap = googleMap;
                subscriber.onNext(true);
                subscriber.onCompleted();
            });
        });
    }

    public static void redrawMarkers() {
        Log.e("REACTX", "Redraw markers");
        mMap.clear();
        ContentController.elements().subscribe(MapController::addMarker);
    }

    private static void addMarker(Point point) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(point.getType().drawable);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point.getLatLng())
                .icon(icon)
                .anchor(0.5f, 1)
                .alpha(alpha(point));
        Marker marker = mMap.addMarker(markerOptions);
        point.setMarkerId(marker.getId());
    }

    private static float alpha(Point point) {
        if (point.getType().equals(PointType.CAMERA)) return 1;
        return Math.min(Math.max(4 - (point.getAge() - point.getKarma() * 5) / 60, 0), 1);
    }
}
