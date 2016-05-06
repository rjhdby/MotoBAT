package info.mototimes.motobat.content;

import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by rjhdby on 06.05.16.
 */
public interface Point {
    PointType getType();

    LatLng getLatLng();

    float getAlpha();

    MarkerOptions getMarkerOptions();

    View getMarkerInfoView();
}
