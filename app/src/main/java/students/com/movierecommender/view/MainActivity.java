package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.fragments.ActorsFragment;
import students.com.movierecommender.view.fragments.MoviesFragment;
import students.com.movierecommender.view.fragments.RecommendedMoviesFragment;
import students.com.movierecommender.viewmodel.ActorViewModel;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private List<Movie> movies;
    private List<Actor> actors;
    private MovieViewModel movieViewModel;
    private ActorViewModel actorViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, this::renderMovieList);

        actorViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorViewModel.class);
        actorViewModel.getActorsLiveData().observe(this, this::renderActorList);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        movieViewModel.hitAllMovies();
        loadFragment(RecommendedMoviesFragment.newInstance(movies));
    }

    private void renderMovieList(List<Movie> movies) {
        this.movies = movies;
    }

    private void renderActorList(List<Actor> actors) {
        this.actors = actors;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_recommendations:
                movieViewModel.hitAllMovies();
                loadFragment(RecommendedMoviesFragment.newInstance(movies));
                return true;
            case R.id.action_actors:
                actorViewModel.hitAllActors();
                loadFragment(ActorsFragment.newInstance(actors));
                return true;
            case R.id.action_movies:
                fragment = new MoviesFragment();
                loadFragment(fragment);
                return true;
        }
        return false;
    };

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}