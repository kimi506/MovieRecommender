package students.com.movierecommender.data.rest;

import io.reactivex.Single;
import retrofit2.http.GET;
import students.com.movierecommender.data.model.Actor;

import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
public interface ActorService {
    @GET("/Actors")
    Single<List<Actor>> getAllActors();
}
