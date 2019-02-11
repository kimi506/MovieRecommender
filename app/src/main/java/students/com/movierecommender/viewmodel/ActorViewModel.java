package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.rest.ActorRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class ActorViewModel extends ViewModel {
    private final ActorRepository actorRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<Actor>> actorsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Actor>> actorsByMovieLiveData = new MutableLiveData<>();
    private final MutableLiveData<Actor> actorLiveData = new MutableLiveData<>();

    public ActorViewModel(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    public MutableLiveData<List<Actor>> getActorsLiveData() {
        return actorsLiveData;
    }

    public MutableLiveData<Actor> getActorLiveData() {
        return actorLiveData;
    }

    public MutableLiveData<List<Actor>> getActorsByMovieLiveData() {
        return actorsByMovieLiveData;
    }

    public void hitAllActors() {
        disposables.add(actorRepository.getAllActors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        actorsLiveData::setValue,
                        throwable -> actorsLiveData.setValue(Arrays.asList(new Actor()))
                ));
    }

    public void hitActorById(Integer idActor) {
        disposables.add(actorRepository.getActorById(idActor)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        actorLiveData::setValue,
                        throwable -> actorLiveData.setValue(new Actor())
                ));
    }

    public void hitActorByIdMovie(Integer idMovie) {
        disposables.add(actorRepository.getActorsByIdMovie(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        actorsByMovieLiveData::setValue,
                        throwable -> actorsByMovieLiveData.setValue(Arrays.asList(new Actor()))
                ));
    }
}
