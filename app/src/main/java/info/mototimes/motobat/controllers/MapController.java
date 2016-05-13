package info.mototimes.motobat.controllers;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.concurrent.TimeUnit;

import info.mototimes.motobat.common.Point;
import info.mototimes.motobat.dictionaries.PointType;
import rx.Observable;

public class MapController {
    private static GoogleMap mMap;
    public static Observable<Boolean> initTrigger = Observable
            .just(true)
            .repeatWhen(b -> b.delay(1, TimeUnit.SECONDS))
            .map(b -> mMap != null)
            .filter(b -> b)
            .take(1);

    public static void init(SupportMapFragment mapFragment) {
        if (mMap != null) return;
        mapFragment.setRetainInstance(true); //При пересоздании активити, для фрагмента не вызываются методы onDestroy() и onCreate().
        mapFragment.getMapAsync(googleMap -> {
            mMap = googleMap;
            mMap.setBuildingsEnabled(true);
            PermissionController.locationPermissionTrigger.subscribe(b -> mMap.setMyLocationEnabled(true));
            redrawMarkers();
        });
    }

    public static void redrawMarkers() {
        mMap.clear();
        ContentController.elements().subscribe(MapController::addMarker);
    }

    private static void addMarker(Point point) {
        BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(point.getType().drawable);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(point.getLatLng())
                .icon(icon)
                .anchor(0.5f, 1)
                .alpha(alpha(point))
                .title(point.getText());
        Marker marker = mMap.addMarker(markerOptions);
        point.setMarkerId(marker.getId());
    }

    private static float alpha(Point point) {
        if (point.getType().equals(PointType.CAMERA)) return 1;
        return Math.min(Math.max(4 - (point.getAge() - point.getKarma() * 5) / 60, 0), 1);
    }
}
