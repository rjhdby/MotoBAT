package info.mototimes.motobat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.concurrent.TimeUnit;

import info.mototimes.motobat.controllers.ContentController;
import info.mototimes.motobat.controllers.MapController;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends FragmentActivity {

    Subscription update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void subscribeServices() {
        update = ContentController.fetch().repeatWhen(b -> b.delay(30, TimeUnit.MINUTES))
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribe(r -> MapController.redrawMarkers());
    }

    private void unSubscribeServices() {
        update.unsubscribe();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unSubscribeServices();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (MapController.isInitialized()) {
            subscribeServices();
        } else {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            MapController.init(mapFragment).subscribe(b -> subscribeServices());
        }
    }
}
