package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import dagger.android.AndroidInjection;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.*;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.fragments.ActorsFragment;
import students.com.movierecommender.view.fragments.MoviesFragment;
import students.com.movierecommender.view.fragments.ReviewsFragment;
import students.com.movierecommender.viewmodel.ActorViewModel;
import students.com.movierecommender.viewmodel.DirectorViewModel;
import students.com.movierecommender.viewmodel.MovieViewModel;
import students.com.movierecommender.viewmodel.ReviewViewModel;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class SingleMovieActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private List<Actor> actors;

    private MovieViewModel movieViewModel;
    private ActorViewModel actorViewModel;
    private DirectorViewModel directorViewModel;
    private ReviewViewModel reviewViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_movie);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getMovie().observe(this, this::renderMovie);
        movieViewModel.getMovieTypesLiveData().observe(this, this::renderMovieTypes);

        actorViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorViewModel.class);
        actorViewModel.getActorsByMovieLiveData().observe(this, this::renderActorList);

        directorViewModel = ViewModelProviders.of(this, viewModelFactory).get(DirectorViewModel.class);
        directorViewModel.getDirectorsByMovieLiveData().observe(this, this::renderDirector);

        reviewViewModel = ViewModelProviders.of(this, viewModelFactory).get(ReviewViewModel.class);
        reviewViewModel.getReviewsLiveDataByMovie().observe(this, this::renderReviews);

        Integer movieId = Integer.parseInt(getIntent().getStringExtra("movieId"));

        movieViewModel.hitMovieById(movieId);
        movieViewModel.hitMovieTypes(movieId);
        actorViewModel.hitActorByIdMovie(movieId);
        directorViewModel.hitDirectorByIdMovie(movieId);
        reviewViewModel.hitReviewsByIdMovie(movieId);
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

    private void renderMovieTypes(List<MovieType> types) {
        TextView typesLabel = findViewById(R.id.types);
        String typesToShow = types.stream().map(type -> type.getName()).collect(Collectors.joining(", "));

        typesLabel.setText(typesToShow);
    }

    private void renderDirector(List<Director> directors) {
        TextView directorsTxt= findViewById(R.id.directors);

        String directorsToShow = directors.stream().map(director -> director.getName() + " " + director.getSurname()).collect(Collectors.joining(", "));
        directorsTxt.setText(directorsToShow);
    }

    private void renderReviews(List<Review> reviews) {
        loadFragment(ReviewsFragment.newInstance(reviews), R.id.reviews_frame_container);
    }

    private void renderActorList(List<Actor> actors) {
        loadFragment(ActorsFragment.newInstance(actors), R.id.actors_frame_container);
    }

    private void loadFragment(Fragment fragment, Integer frameContainer) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(frameContainer, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addReview(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SingleMovieActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.dialog_review, null);
        mBuilder.setView(mView);
        mBuilder.create();
        final AlertDialog dialog = mBuilder.create();
        dialog.show();

        Button submitReviewButton = (Button) mView.findViewById(R.id.btn_submit);
        TextView reviewText = mView.findViewById(R.id.review_text);
        RatingBar reviewRating = mView.findViewById(R.id.review_rating);

        submitReviewButton.setOnClickListener(view1 -> {
            Review review = new Review();
            review.setRating((int)reviewRating.getRating());
            review.setText(reviewText.getText().toString());
            review.setUserId(1);
            review.setMovieId(1);
            reviewViewModel.insertReview(review);
        });
    }
}
