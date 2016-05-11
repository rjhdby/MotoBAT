package info.mototimes.motobat.common;

import com.google.android.gms.maps.model.LatLng;

import info.mototimes.motobat.dictionaries.PointAlignment;
import info.mototimes.motobat.dictionaries.PointType;
import info.mototimes.motobat.models.PointModel;

public class Point {
    private int            id;
    private int            created;
    private LatLng         latLng;
    private int            karma;
    private PointAlignment alignment;
    private PointType      type;
    private String         text;

    public Point(PointModel model) {
        id = model.id;
        created = model.created;
        latLng = new LatLng(model.lat, model.lon);
        karma = model.karma;
        alignment = PointAlignment.parse(model.alignment);
        type = PointType.parse(model.type);
        text = model.text;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public PointType getType() {
        return type;
    }
}
