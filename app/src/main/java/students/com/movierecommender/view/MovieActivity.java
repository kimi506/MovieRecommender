package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import students.com.movierecommender.MyApplication;
import students.com.movierecommender.R;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);
        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.movieResponse().observe(this, this::renderMovieList);
    }

    private void renderMovieList(List<Movie> movies) {
        Toast.makeText(MovieActivity.this, movies.get(0).toString(), Toast.LENGTH_SHORT).show();
    }

    public void showAllMovie(View view) {
        movieViewModel.getAllMovies();
    }
}
