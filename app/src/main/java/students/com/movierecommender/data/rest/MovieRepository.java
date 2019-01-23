package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.model.Movie;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class MovieRepository {

    private MovieService movieService;

    @Inject
    public MovieRepository(MovieService movieService) {
        this.movieService = movieService;
    }

    public Observable<List<Movie>> getAllMovies() {
            return movieService.getAllMovies();
    }
}
