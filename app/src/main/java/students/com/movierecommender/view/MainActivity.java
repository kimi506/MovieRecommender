package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.fragments.ActorsFragment;
import students.com.movierecommender.view.fragments.MoviesFragment;
import students.com.movierecommender.viewmodel.ActorViewModel;
import students.com.movierecommender.viewmodel.MovieViewModel;
import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private List<Movie> recommendedMovies;
    private List<Movie> movies;
    private List<Actor> actors;
    private MovieViewModel movieViewModel;
    private ActorViewModel actorViewModel;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        spinner = findViewById(R.id.progressBarMovies);
        spinner.setVisibility(View.VISIBLE);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, this::renderMovieList);

        actorViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorViewModel.class);
        actorViewModel.getActorsLiveData().observe(this, this::renderActorList);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        movieViewModel.hitAllMovies();
        actorViewModel.hitAllActors();
    }

    private void renderMovieList(List<Movie> movies) {
        if (this.movies == null) {
            this.movies = movies;
            loadFragment(MoviesFragment.newInstance(movies));
        } else {
            this.movies = movies;
        }
    }

    private void renderActorList(List<Actor> actors) {
        this.actors = actors;
        spinner.setVisibility(View.GONE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.action_recommendations:
                loadFragment(MoviesFragment.newInstance(movies));
                return true;
            case R.id.action_actors:
                loadFragment(ActorsFragment.newInstance(actors));
                return true;
            case R.id.action_movies:
                loadFragment(MoviesFragment.newInstance(movies));
                return true;
        }
        return false;
    };

    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        super.onCreateSupportNavigateUpTaskStack(builder);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}