package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.database.dao.LocalMovieService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class MovieRepository {
    private static int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final MovieService movieService;
    private final LocalMovieService localMovieService;
    private final Executor executor;


    @Inject
    public MovieRepository(MovieService movieService, LocalMovieService localMovieService, Executor executor) {
        this.movieService = movieService;
        this.localMovieService = localMovieService;
        this.executor = executor;
    }

    public Observable<List<Movie>> getAllMovies() {
        return movieService.getAllMovies();
    }

    public Observable<Movie> getMovieById(Integer id) {
        return movieService.getMovieById(id);
    }

}

