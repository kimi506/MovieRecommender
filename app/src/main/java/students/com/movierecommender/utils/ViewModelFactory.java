package students.com.movierecommender.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import students.com.movierecommender.data.rest.ActorRepository;
import students.com.movierecommender.data.rest.MovieRepository;
import students.com.movierecommender.viewmodel.ActorViewModel;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;

/**
 * Created by Kamil Gonska on sty, 2019
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository movieRepository;
    private final ActorRepository actorRepository;

    @Inject
    public ViewModelFactory(MovieRepository movieRepository, ActorRepository actorRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(ActorViewModel.class)) {
            return (T) new ActorViewModel(actorRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }

}