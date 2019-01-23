package students.com.movierecommender.data.rest;

import android.arch.lifecycle.MutableLiveData;
import io.reactivex.Single;
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

    public Single<List<Actor>> getAllActors(){
        final MutableLiveData<List<Actor>> actors =new MutableLiveData<>();
        return actorService.getAllActors();
    }
}
