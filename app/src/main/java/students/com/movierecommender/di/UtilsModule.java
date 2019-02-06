package students.com.movierecommender.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import students.com.movierecommender.data.rest.MovieRepository;
import students.com.movierecommender.data.rest.MovieService;
import students.com.movierecommender.database.DatabaseConfig;
import students.com.movierecommender.database.dao.LocalMovieService;
import students.com.movierecommender.database.dao.LocalMovieServiceImpl;
import students.com.movierecommender.database.dao.MovieDao;
import students.com.movierecommender.utils.Urls;
import students.com.movierecommender.utils.ViewModelFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kamil Gonska on sty, 2019
 */

@Module
public class UtilsModule {

    @Provides
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return builder.setLenient().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    MovieService provideMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    OkHttpClient getRequestHeader() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .build();
            return chain.proceed(request);
        })
                .connectTimeout(100, TimeUnit.SECONDS)
                .writeTimeout(100, TimeUnit.SECONDS)
                .readTimeout(300, TimeUnit.SECONDS);
        return httpClient.build();
    }

    @Provides
    DatabaseConfig provideDatabase(Application application) {
        //TODO
        // allowMainThread is not recommended - FIXME
        return Room.databaseBuilder(application,
                DatabaseConfig.class, "MyDatabase.db")
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    MovieDao provideMovieDao(DatabaseConfig database) {
        return database.movieDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    LocalMovieService localMovieService(MovieDao movieDao) {
        return new LocalMovieServiceImpl(movieDao);
    }

    @Provides
    MovieRepository provideMovieRepository(MovieService movieService, LocalMovieService localMovieService, Executor executor) {
        return new MovieRepository(movieService, localMovieService, executor);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(MovieRepository movieRepository) {
        return new ViewModelFactory(movieRepository);
    }
}
