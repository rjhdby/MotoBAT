package info.mototimes.motobat.controllers;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import info.mototimes.motobat.R;
import info.mototimes.motobat.common.Point;
import info.mototimes.motobat.dictionaries.PointType;
import rx.Observable;

public class MapController {
    private static GoogleMap        mMap;
    private static FragmentActivity mActivity;

    public static boolean isInitialized() {
        return mMap != null;
    }

    public static Observable<Boolean> init(FragmentActivity activity) {
        return Observable.create(subscriber -> {
            mActivity = activity;
            SupportMapFragment mapFragment = (SupportMapFragment) activity.getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(googleMap -> {
                mMap = googleMap;
                mMap.setBuildingsEnabled(true);
                addUserLocation();
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

    private static boolean isPermitted(String permission) {
        return android.os.Build.VERSION.SDK_INT < 23 || mActivity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    private static void addUserLocation() {
        if (isPermitted(Manifest.permission.ACCESS_FINE_LOCATION) || isPermitted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            mMap.setMyLocationEnabled(true);
        }
    }
}
