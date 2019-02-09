package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import io.reactivex.annotations.Nullable;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.List;

public class MovieActivity extends AppCompatActivity {

    List<Movie> movies;
    ListView listView;
    private static MovieAdapter adapter;

    @Inject
    ViewModelFactory viewModelFactory;
    MovieViewModel movieViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getAllMovies().observe(this, this::renderMovieList);
        movieViewModel.getMovie().observe(this, this::renderMovie);

        listView = findViewById(R.id.list);

        movieViewModel.hitAllMovies();
    }

    private void renderMovieList(List<Movie> movies) {
        this.movies = movies;

        adapter = new MovieAdapter(movies, getApplicationContext());
        listView.setAdapter(adapter);
    }

    private void renderMovie(Movie movies) {
        Toast.makeText(MovieActivity.this, movies.toString(), Toast.LENGTH_SHORT).show();
    }

    public void showAllMovie(View view) {
        movieViewModel.hitAllMovies();
    }

//    public void showMovieById(View view) {
//        movieViewModel.hitMovieById(Integer.parseInt(idMovie.getText().toString()));
//    }
}
