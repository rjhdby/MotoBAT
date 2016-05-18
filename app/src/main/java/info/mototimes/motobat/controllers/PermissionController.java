package info.mototimes.motobat.controllers;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import java.util.concurrent.TimeUnit;

import rx.Observable;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class PermissionController {
    private static final int LOCATION_PERMISSION = 1;

    private static Activity activity;
    public static Observable<Boolean> locationPermissionTrigger = Observable
            .just(true)
            .repeatWhen(b -> b.delay(1, TimeUnit.SECONDS))
            .map(b -> (activity != null) && (isPermitted(ACCESS_FINE_LOCATION) || isPermitted(ACCESS_COARSE_LOCATION)))
            .filter(b -> b)
            .take(1);

    private static void locationPermission() {
        if (isPermitted(ACCESS_FINE_LOCATION) || isPermitted(ACCESS_COARSE_LOCATION)) return;
        ActivityCompat.requestPermissions(activity,
                                          new String[]{ ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION },
                                          LOCATION_PERMISSION);
    }

    public static void init(Activity activity) {
        PermissionController.activity = activity;
        if (android.os.Build.VERSION.SDK_INT < 23) return;
        locationPermission();
    }

    private static boolean isPermitted(String permission) {
        return android.os.Build.VERSION.SDK_INT < 23 || activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }
}
