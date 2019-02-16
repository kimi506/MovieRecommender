package students.com.movierecommender.view;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
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

public class SingleActorActivity extends AppCompatActivity {
    @Inject
    ViewModelFactory viewModelFactory;

    private ActorViewModel actorViewModel;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_actor);

        actorViewModel = ViewModelProviders.of(this, viewModelFactory).get(ActorViewModel.class);
        actorViewModel.getActorLiveData().observe(this, this::renderActor);

        movieViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieViewModel.class);
        movieViewModel.getMoviesByIdActorLiveData().observe(this, this::renderMovies);

        Integer actorId = Integer.parseInt(getIntent().getStringExtra("actorId"));

        actorViewModel.hitActorById(actorId);
        movieViewModel.hiMoviesByIdActor(actorId);
    }

    private void renderActor(Actor actor) {
        ImageView image = findViewById(R.id.image);
        TextView name = findViewById(R.id.name);

        Bitmap bmp = BitmapFactory.decodeByteArray(actor.getImage(), 0, actor.getImage().length);
        image.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                bmp.getHeight(), false));

        name.setText(actor.getName() + " " + actor.getSurname());

    }

    private void renderMovies(List<Movie> movies) {
        loadMovies(MoviesFragment.newInstance(movies));
    }

    private void loadMovies(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
