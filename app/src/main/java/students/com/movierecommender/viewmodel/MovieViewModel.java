package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.data.rest.MovieRepository;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Kamil Gonska on sty, 2019
 */

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<List<Movie>> moviesLiveData = new MutableLiveData<>();
    private final MutableLiveData<Movie> movieLiveData = new MutableLiveData<>();

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MutableLiveData<List<Movie>> getAllMovies() {
        return moviesLiveData;
    }

    public MutableLiveData<Movie> getMovie() {
        return movieLiveData;
    }

    public void hitAllMovies() {
        disposables.add(movieRepository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> moviesLiveData.setValue(result),
                        throwable -> moviesLiveData.setValue(Arrays.asList(new Movie()))
                ));
    }

    public void hitMovieById(Integer id) {
        disposables.add(movieRepository.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> movieLiveData.setValue(result),
                        throwable -> movieLiveData.setValue(new Movie())
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
