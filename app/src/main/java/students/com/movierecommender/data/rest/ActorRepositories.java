package students.com.movierecommender.data.rest;

import io.reactivex.Observable;
import students.com.movierecommender.data.model.Actor;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */
@Singleton
public class ActorRepositories {
    private final ActorService actorService;

    @Inject
    public ActorRepositories(ActorService actorService) {
        this.actorService = actorService;
    }

    public Observable<List<Actor>> getAllActors() {
        return actorService.getAllActors();
    }

    public Observable<Actor> getActorById(Integer id) {
        return actorService.getActorById(id);
    }

}
