package students.com.movierecommender.utils;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import students.com.movierecommender.data.rest.*;
import students.com.movierecommender.viewmodel.*;

import javax.inject.Inject;

/**
 * Created by Kamil Gonska on sty, 2019
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository movieRepository;
    private final ActorRepository actorRepository;
    private final AuthRepository authRepository;
    private final DirectorRepository directorRepository;
    private final ReviewRepository reviewRepository;

    @Inject
    public ViewModelFactory(MovieRepository movieRepository, ActorRepository actorRepository, AuthRepository authRepository, DirectorRepository directorRepository, ReviewRepository reviewRepository) {
        this.movieRepository = movieRepository;
        this.actorRepository = actorRepository;
        this.authRepository = authRepository;
        this.directorRepository = directorRepository;
        this.reviewRepository = reviewRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(ActorViewModel.class)) {
            return (T) new ActorViewModel(actorRepository);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(authRepository);
        } else if (modelClass.isAssignableFrom(DirectorViewModel.class)) {
            return (T) new DirectorViewModel(directorRepository);
        } else if (modelClass.isAssignableFrom(ReviewViewModel.class)) {
            return (T) new ReviewViewModel(reviewRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }

}