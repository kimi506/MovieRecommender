package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.SharedPrefHelper;
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

    private List<Movie> movies;
    private List<Movie> recommendedMovies;
    private List<Actor> actors;
    private MovieViewModel movieViewModel;
    private ActorViewModel actorViewModel;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPrefHelper.getInstance().Initialize(getApplicationContext());
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        spinner = findViewById(R.id.progressBarMovies);
        spinner.setVisibility(View.VISIBLE);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, this::renderMovieList);
        movieViewModel.getMoviesRecommendedLiveData().observe(this, this::renderRecommendedMoviesList);

        actorViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorViewModel.class);
        actorViewModel.getActorsLiveData().observe(this, this::renderActorList);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Drawable upArrow = getResources().getDrawable(R.drawable.back_arrow);
        upArrow.setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        movieViewModel.hitAllMovies();
        movieViewModel.hitRecommendedMovie(SharedPrefHelper.getInstance().getIdUser());
        actorViewModel.hitAllActors();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.about_dev:
                showDialog();
                break;
            case R.id.logout:
                SharedPrefHelper.getInstance().removeToken();
                finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDialog() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this, R.style.Theme_AppCompat_Light_Dialog_Alert).setTitle(R.string.aboutDev).setMessage(R.string.authors);

        final AlertDialog alert = dialog.create();
        alert.show();

        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alert.isShowing()) {
                    alert.dismiss();
                }
            }
        };

        alert.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, 5000);
    }

    private void renderMovieList(List<Movie> movies) {
        this.movies = movies;
    }

    private void renderRecommendedMoviesList(List<Movie> recommendedMovies) {
        if (this.recommendedMovies == null) {
            this.recommendedMovies = recommendedMovies;
            loadFragment(MoviesFragment.newInstance(recommendedMovies));
        } else {
            this.recommendedMovies = recommendedMovies;
        }
    }

    private void renderActorList(List<Actor> actors) {
        this.actors = actors;
        spinner.setVisibility(View.GONE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.action_recommendations:
                loadFragment(MoviesFragment.newInstance(recommendedMovies));
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