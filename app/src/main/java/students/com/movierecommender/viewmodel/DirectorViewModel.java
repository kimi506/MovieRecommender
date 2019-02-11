package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import students.com.movierecommender.data.entity.Director;
import students.com.movierecommender.data.rest.DirectorRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kamil Gonska on lut, 2019
 */
public class DirectorViewModel extends ViewModel {
    private final DirectorRepository directorRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    private final MutableLiveData<List<Director>> directorsLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Director>> directorsByMovieLiveData = new MutableLiveData<>();
    private final MutableLiveData<Director> directorLiveData = new MutableLiveData<>();

    public DirectorViewModel(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public MutableLiveData<List<Director>> getDirectorsLiveData() {
        return directorsLiveData;
    }

    public MutableLiveData<Director> getDirectorLiveData() {
        return directorLiveData;
    }

    public MutableLiveData<List<Director>> getDirectorsByMovieLiveData() {
        return directorsByMovieLiveData;
    }

    public void hitAllDirectors() {
        disposables.add(directorRepository.getAllDirectors()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        directorsLiveData::setValue,
                        throwable -> directorsLiveData.setValue(Arrays.asList(new Director()))
                ));
    }

    public void hitDirectorById(Integer idDirector) {
        disposables.add(directorRepository.getDirectorById(idDirector)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        directorLiveData::setValue,
                        throwable -> directorLiveData.setValue(new Director())
                ));
    }

    public void hitDirectorByIdMovie(Integer idMovie) {
        disposables.add(directorRepository.getDirectorsByIdMovie(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        directorsByMovieLiveData::setValue,
                        throwable -> directorsByMovieLiveData.setValue(Arrays.asList(new Director()))
                ));
    }
}
