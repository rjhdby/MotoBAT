package info.mototimes.motobat.content;

import java.util.List;

/**
 * Created by rjhdby on 06.05.16.
 */
public class Store {
    private static Store ourInstance = new Store();
    List<Point> points;

    private Store() {
    }

    public static Store getInstance() {
        return ourInstance;
    }
}
