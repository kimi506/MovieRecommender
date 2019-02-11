package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.List;

public class SingleMovieActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, this::renderMovie);

        Integer movieId = Integer.parseInt(getIntent().getStringExtra("movieId"));

        movieViewModel.hitMovieById(movieId);
    }

    private void renderMovie(Movie movie) {
        ImageView image = findViewById(R.id.front_image);
        TextView name = findViewById(R.id.name);
        RatingBar ratingBar = findViewById(R.id.rating);

        Bitmap bmp = BitmapFactory.decodeByteArray(movie.getFrontImage(), 0, movie.getFrontImage().length);
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                bmp.getHeight(), false));

        name.setText(movie.getName() + " (" + movie.getProductionYear() + ")");
        ratingBar.setRating(movie.getRating());
    }
}
