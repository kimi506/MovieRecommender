package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import students.com.movierecommender.data.entity.Director;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.Urls;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface DirectorService {
    @GET(Urls.DIRECTORS)
    Observable<List<Director>> getAllDirectors();

    @GET(Urls.DIRECTORS + "{id}")
    Observable<Director> getDirectorById(@Path("id")Integer id);

    @GET(Urls.MOVIES + "{idMovie}/" + Urls.DIRECTORS)
    Observable<List<Director>> getDirectorsByIdMovie(@Path("idMovie") Integer idMovie);

}
