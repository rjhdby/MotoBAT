package info.mototimes.motobat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.concurrent.TimeUnit;

import info.mototimes.motobat.controllers.ContentController;
import info.mototimes.motobat.controllers.MapController;
import info.mototimes.motobat.controllers.PermissionController;
import info.mototimes.motobat.controllers.PreferencesController;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

public class MainActivity extends FragmentActivity {
    private static Subscription updatePoints;
    private static Subscription updateCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferencesController.init(this);
        PermissionController.init(this);
        MapController.init((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map));
    }

    private void startUpdateScheduling() {
        updatePoints = ContentController.fetch()
                                        .repeatWhen(b -> b.delay(5, TimeUnit.MINUTES))
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(r -> MapController.redrawMarkers());
        updateCamera = MapController.updateCamera.subscribe(PreferencesController::updateCamera);

    }

    private void stopUpdateScheduling() {
        if (updatePoints != null && !updatePoints.isUnsubscribed()) updatePoints.unsubscribe();
        if (updateCamera != null && !updateCamera.isUnsubscribed()) updateCamera.unsubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUpdateScheduling();
    }

    @Override
    protected void onResume() {
        super.onResume();
        MapController.initTrigger.subscribe(b -> startUpdateScheduling());
    }
}
