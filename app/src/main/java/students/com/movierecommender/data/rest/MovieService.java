package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.Urls;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface MovieService {
    @GET(Urls.MOVIES)
    Observable<List<Movie>> getAllMovies();

    @GET(Urls.MOVIES + "{id}")
    Observable<Movie> getMovieById(@Path("id") Integer id);


}
