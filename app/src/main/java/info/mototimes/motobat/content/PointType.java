package info.mototimes.motobat.content;

/**
 * Created by rjhdby on 06.05.16.
 */
public enum PointType {
    POLICE_GS,
    POLICE_RT,
    POLICE_CAR,
    CAMERA,
    UNKNOWN;

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
