package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import students.com.movierecommender.data.model.Actor;
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
}
