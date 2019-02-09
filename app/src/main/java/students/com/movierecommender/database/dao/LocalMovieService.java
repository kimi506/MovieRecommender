package students.com.movierecommender.database.dao;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Movie;

import java.util.List;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public interface LocalMovieService {
    Observable<List<Movie>> getAllMovies();
    void saveMovies(List<Movie> movies);

}
