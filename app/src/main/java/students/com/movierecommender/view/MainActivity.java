package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import students.com.movierecommender.MyApplication;
import students.com.movierecommender.R;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.adapter.MovieAdapter;
import students.com.movierecommender.view.fragments.ActorsFragment;
import students.com.movierecommender.view.fragments.MoviesFragment;
import students.com.movierecommender.view.fragments.RecommendedMoviesFragment;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private List<Movie> movies;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, this::renderMovieList);
        movieViewModel.getMovie().observe(this, this::renderMovie);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        movieViewModel.hitAllMovies();
    }

    private void renderMovieList(List<Movie> movies) {
        this.movies = movies;
        loadFragment(RecommendedMoviesFragment.newInstance(movies));
    }

    private void renderMovie(Movie movies) {
        Toast.makeText(MainActivity.this, movies.toString(), Toast.LENGTH_SHORT).show();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.action_recommendations:
                loadFragment(RecommendedMoviesFragment.newInstance(movies));
                return true;
            case R.id.action_actors:
                fragment = new ActorsFragment();
                loadFragment(fragment);
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