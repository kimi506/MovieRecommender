package students.com.movierecommender.database.dao;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Movie;

import java.util.List;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class LocalMovieServiceImpl implements LocalMovieService {

    private final MovieDao movieDao;

    public LocalMovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Observable<List<Movie>> getAllMovies() {
        return Observable.fromCallable(() -> movieDao.getAllMovie());
    }

    @Override
    public void saveMovies(List<Movie> movies) {
        if (movies != null && !movies.isEmpty())
            movieDao.saveMovies(movies);
    }
}
