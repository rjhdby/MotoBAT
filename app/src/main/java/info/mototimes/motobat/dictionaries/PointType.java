package info.mototimes.motobat.dictionaries;

import info.mototimes.motobat.R;

/**
 * Created by rjhdby on 06.05.16.
 */
public enum PointType {
    POLICE_GS(R.drawable.gs),
    POLICE_RT(R.drawable.rt),
    POLICE_CAR(R.drawable.car),
    CAMERA(R.drawable.camera),
    UNKNOWN(R.drawable.car);

    public final int drawable;

    PointType(int drawable) {this.drawable = drawable;}

    public static PointType parse(String type) {
        switch (type) {
            case "GS":
                return POLICE_GS;
            case "RT":
                return POLICE_RT;
            case "CAR":
                return POLICE_CAR;
            case "CAMERA":
                return CAMERA;
            default:
                return UNKNOWN;
        }
    }
}
