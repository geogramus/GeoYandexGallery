package geogram.example.geoyandexgallery.rest.api;

import geogram.example.geoyandexgallery.rest.models.GeneralModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by geogr on 23.04.2018.
 */

public interface ImageService {
    String header="Authorization: OAuth AQAAAAAl58iKAADLWwMHDJ2qOUS4kgTuqylgprU";
    @Headers(header)
    @GET("resources?preview_size=M")
    Observable<GeneralModel> get_posts(@Query("path")String type, @Query("offset") int offset);

}
