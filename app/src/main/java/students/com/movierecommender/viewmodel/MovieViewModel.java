package students.com.movierecommender.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.data.entity.MovieType;
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
    private final MutableLiveData<List<Movie>> moviesRecommendedLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<MovieType>> movieTypesLiveData = new MutableLiveData<>();
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

    public MutableLiveData<List<Movie>> getMoviesRecommendedLiveData() {
        return moviesRecommendedLiveData;
    }

    public MutableLiveData<List<MovieType>> getMovieTypesLiveData() {
        return movieTypesLiveData;
    }

    public void hitAllMovies() {
        disposables.add(movieRepository.getAllMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        moviesLiveData::setValue,
                        throwable -> moviesLiveData.setValue(Arrays.asList(new Movie()))
                ));
    }

    public void hitMovieById(Integer id) {
        disposables.add(movieRepository.getMovieById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieLiveData::setValue,
                        throwable ->
                                movieLiveData.setValue(new Movie())
                ));
    }

    public void hitRecommendedMovie(Integer idUser) {
        disposables.add(movieRepository.getRecommendedMovies(idUser)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        moviesRecommendedLiveData::setValue,
                        throwable ->
                                getMoviesRecommendedLiveData().setValue(Arrays.asList(new Movie()))
                ));
    }

    public void hitMovieTypes(Integer idMovie) {
        disposables.add(movieRepository.getMovieTypes(idMovie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        movieTypesLiveData::setValue,
                        throwable -> movieTypesLiveData.setValue(Arrays.asList(new MovieType()))
                ));
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
