package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import io.reactivex.annotations.Nullable;
import students.com.movierecommender.MyApplication;
import students.com.movierecommender.R;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.adapter.MovieAdapter;
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

//    @BindView(R.id.id_movie)
//    EditText idMovie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        ButterKnife.bind(this);
//        ((MyApplication) getApplication()).getAppComponent().doInjection(this);

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
