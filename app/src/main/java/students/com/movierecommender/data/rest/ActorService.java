package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Director;
import students.com.movierecommender.utils.Urls;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface ActorService {
    @GET(Urls.ACTORS)
    Observable<List<Actor>> getAllActors();

    @GET(Urls.ACTORS + "{id}")
    Observable<Actor> getActorById(@Path("id") Integer id);

    @GET(Urls.MOVIES + "{idMovie}/" + Urls.ACTORS)
    Observable<List<Actor>> getActorsByIdMovie(@Path("idMovie") Integer idMovie);
}
