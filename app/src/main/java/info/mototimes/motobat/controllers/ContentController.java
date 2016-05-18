package info.mototimes.motobat.controllers;

import java.util.ArrayList;

import info.mototimes.motobat.common.Point;
import info.mototimes.motobat.services.MototimesRequestService;
import rx.Observable;

public class ContentController {
    private static ArrayList<Point> points = new ArrayList<>();

    public static Observable<Boolean> fetch() {
        ArrayList<Point> newPoints = new ArrayList<>();
        return Observable.create(subscriber -> {
            MototimesRequestService.api.getList()
                                       .flatMap(listModel -> Observable.from(listModel.data))
                                       .subscribe(pointModel -> newPoints.add(new Point(pointModel))
                                               , e -> {}
                                               , () -> {
                                           points = new ArrayList<>(newPoints);
                                           subscriber.onNext(true);
                                           subscriber.onCompleted();
                                       });
        });
    }

    public static Observable<Point> elements() {
        return Observable.from(points);
    }
}
