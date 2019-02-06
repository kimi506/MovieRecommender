package students.com.movierecommender.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.database.dao.MovieDao;
import students.com.movierecommender.database.util.DateConverter;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Database(entities = {Movie.class}, version = 1)
@TypeConverters(DateConverter.class)
public abstract class DatabaseConfig extends RoomDatabase {

    public abstract MovieDao movieDao();
}
