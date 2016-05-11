package info.mototimes.motobat.services;

import info.mototimes.motobat.models.ListModel;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

public interface MototimesRequestService {
    String                  URL = "http://mototimes.info/v2/";
    MototimesRequestService api = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(MototimesRequestService.class);

    @GET("request.php?m=list")
    Observable<ListModel> getList();
}
