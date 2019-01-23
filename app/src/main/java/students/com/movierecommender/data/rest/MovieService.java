package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.utils.Urls;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface MovieService {
    @GET(Urls.MOVIES)
    Observable<List<Movie>> getAllMovies();
}
