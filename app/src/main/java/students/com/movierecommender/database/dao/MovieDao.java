package students.com.movierecommender.database.dao;

import android.arch.persistence.room.*;
import io.reactivex.Flowable;
import students.com.movierecommender.data.entity.Movie;

import java.util.Date;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long save(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movieList);

    @Query("SELECT * FROM movie")
    List<Movie> getAllMovie();

    @Query("SELECT * FROM movie WHERE id = :idMovie")
    Flowable<Movie> getMovieById(Integer idMovie);

    @Query("SELECT * FROM movie WHERE id = :idMovie AND lastRefresh > :lastRefreshMax LIMIT 1")
    Movie hasMovie(Integer idMovie, Date lastRefreshMax);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);
}
