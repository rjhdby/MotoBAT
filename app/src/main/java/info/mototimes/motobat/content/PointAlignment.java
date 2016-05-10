package info.mototimes.motobat.content;

/**
 * Created by rjhdby on 10.05.16.
 */
public enum PointAlignment {
    GOOD,
    EVIL,
    NEUTRAL,
    UNKNOWN;

    public static PointAlignment parse(int alignment) {
        switch (alignment) {
            case 1:
                return GOOD;
            case 2:
                return EVIL;
            case 3:
                return NEUTRAL;
            default:
                return UNKNOWN;
        }
    }
}
