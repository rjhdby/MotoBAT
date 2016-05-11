package info.mototimes.motobat.content;

import java.util.ArrayList;

import info.mototimes.motobat.common.Point;
import info.mototimes.motobat.models.PointModel;
import info.mototimes.motobat.services.MototimesRequestService;
import rx.Observable;
import rx.Subscriber;

public class Points {
    private static ArrayList<Point> points = new ArrayList<>();

    public static Observable<Boolean> fetch() {
        ArrayList<Point> newPoints = new ArrayList<>();
        return Observable.create(subscriber -> {
            MototimesRequestService.api.getList()
                                       .flatMap(n -> Observable.from(n.data))
                                       .subscribe(new Subscriber<PointModel>() {
                                @Override
                                public void onCompleted() {
                                    points = new ArrayList<>(newPoints);
                                    subscriber.onNext(true);
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(PointModel pointModel) {
                                    newPoints.add(new Point(pointModel));
                                }
                            });
        });
    }

    public static Observable<Point> elements() {
        return Observable.from(points);
    }
}
