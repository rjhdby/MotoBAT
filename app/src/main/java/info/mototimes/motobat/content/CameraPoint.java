package info.mototimes.motobat.content;

import android.view.View;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by rjhdby on 06.05.16.
 */
public class CameraPoint implements Point {
    @Override
    public PointType getType() {
        return PointType.CAMERA;
    }

    @Override
    public LatLng getLatLng() {
        return null;
    }

    @Override
    public float getAlpha() {
        return 0;
    }

    @Override
    public MarkerOptions getMarkerOptions() {
        return null;
    }

    @Override
    public View getMarkerInfoView() {
        return null;
    }
}
