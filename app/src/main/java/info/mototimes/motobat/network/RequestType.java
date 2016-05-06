package info.mototimes.motobat.network;

/**
 * Created by rjhdby on 06.05.16.
 */
public enum RequestType {
    GET_POINTS("list"),
    CREATE_POINT("create");

    public final String code;

    RequestType(String code) {
        this.code = code;
    }
}
