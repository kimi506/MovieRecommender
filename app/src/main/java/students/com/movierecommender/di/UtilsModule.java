package students.com.movierecommender.di;

import android.arch.lifecycle.ViewModelProvider;
import android.util.Base64;
import com.google.gson.*;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import students.com.movierecommender.data.rest.MovieRepository;
import students.com.movierecommender.data.rest.MovieService;
import students.com.movierecommender.utils.Urls;
import students.com.movierecommender.utils.ViewModelFactory;

import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;

/**
 * Created by Kamil Gonska on sty, 2019
 */

@Module
public class UtilsModule {

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder =
                new GsonBuilder();
        builder.registerTypeAdapter(byte[].class, (JsonSerializer<byte[]>) (src, typeOfSrc, context) -> new JsonPrimitive(Base64.encodeToString(src, Base64.NO_WRAP)));
        builder.registerTypeAdapter(byte[].class, (JsonDeserializer<byte[]>) (json, typeOfT, context) -> Base64.decode(json.getAsString(), Base64.NO_WRAP));
        return builder.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @Provides
    @Singleton
    MovieService getMovieService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

    @Provides
    @Singleton
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
    @Singleton
    MovieRepository getMovieRepository(MovieService movieService) {
        return new MovieRepository(movieService);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getViewModelFactory(MovieRepository movieRepository) {
        return new ViewModelFactory(movieRepository);
    }
}
