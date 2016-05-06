package info.mototimes.motobat.network;

import android.util.Log;

import java.util.HashMap;

import rx.Observable;

/**
 * Created by rjhdby on 06.05.16.
 */
public class Request {
    private static final String URL = "http://mototimes.info/request/request.php";

    private HashMap<String, String> params;

    {
        params = new HashMap<>();
    }

    public Request(RequestType type) {
        params.put("m", type.code);
    }

    public okhttp3.Request build() {
        StringBuilder sb = new StringBuilder();
        sb.append(URL).append('?');
        Observable.from(params.entrySet())
                  .map(e -> sb.append(e.getKey()).append("=").append(e.getValue()).append("&"))
                  .subscribe();
        Log.d("REACT BLIA", sb.toString());
        return new okhttp3.Request.Builder().url(sb.toString()).build();
    }

    public Request add(String key, String value) {
        params.put(key, value);
        return this;
    }

}
