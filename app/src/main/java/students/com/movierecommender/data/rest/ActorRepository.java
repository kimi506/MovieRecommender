package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.entity.Actor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class ActorRepository {
    private final ActorService actorService;

    @Inject
    public ActorRepository(ActorService actorService) {
        this.actorService = actorService;
    }

    public Observable<List<Actor>> getAllActors() {
        return actorService.getAllActors();
    }

    public Observable<Actor> getActorById(Integer id) {
        return actorService.getActorById(id);
    }

    public Observable<List<Actor>> getActorsByIdMovie(Integer idMovie) {
        return actorService.getActorsByIdMovie(idMovie);
    }

}
