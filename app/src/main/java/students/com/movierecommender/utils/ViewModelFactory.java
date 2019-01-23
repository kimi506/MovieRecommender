package students.com.movierecommender.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import students.com.movierecommender.data.rest.MovieRepository;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;

/**
 * Created by Kamil Gonska on sty, 2019
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository movieRepository;

    @Inject
    public ViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(movieRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}