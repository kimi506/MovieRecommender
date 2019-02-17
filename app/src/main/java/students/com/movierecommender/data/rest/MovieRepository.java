package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.data.entity.MovieType;
import students.com.movierecommender.database.dao.LocalMovieService;
import students.com.movierecommender.utils.SharedPrefHelper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class MovieRepository {
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

    public Observable<List<Movie>> getRecommendedMovies(Integer idUser) {
        String token = "Bearer " + SharedPrefHelper.getInstance().getToken();
        return movieService.getRecommendedMovies(token, idUser);
    }

    public Observable<List<MovieType>> getMovieTypes(Integer idMovie) {
        return movieService.getMovieTypes(idMovie);
    }

    public Observable<List<Movie>> getMoviesByIdActor(Integer idActor) {
        return movieService.getMoviesByIdActor(idActor);
    }
}

