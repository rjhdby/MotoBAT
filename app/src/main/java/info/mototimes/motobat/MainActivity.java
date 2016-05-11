package info.mototimes.motobat;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.SupportMapFragment;

import info.mototimes.motobat.content.Points;
import info.mototimes.motobat.controllers.MapController;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;


public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        Observable.zip(MapController.init(mapFragment), Points.fetch(), (b, p) -> p)
                  .subscribe(p -> {
                      Points.elements()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(MapController::addMarker);
                  });
    }
}
