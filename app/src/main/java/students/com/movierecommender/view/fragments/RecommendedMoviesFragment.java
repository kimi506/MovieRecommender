package students.com.movierecommender.view.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import students.com.movierecommender.MyApplication;
import students.com.movierecommender.R;
import students.com.movierecommender.data.model.Movie;
import students.com.movierecommender.utils.ViewModelFactory;
import students.com.movierecommender.view.MovieActivity;
import students.com.movierecommender.view.adapter.MovieAdapter;
import students.com.movierecommender.viewmodel.MovieViewModel;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class RecommendedMoviesFragment extends Fragment {

    private MovieAdapter adapter;

    public static Fragment newInstance(List<Movie> movies)
    {
        RecommendedMoviesFragment recommendedMoviesFragment = new RecommendedMoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movies", new ArrayList<>(movies));
        recommendedMoviesFragment.setArguments(bundle);
        return recommendedMoviesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recommended_movies, container, false);
        ListView listView = rootView.findViewById(R.id.list);
        adapter = new MovieAdapter((ArrayList<Movie>)getArguments().getSerializable("movies"), getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        return rootView;
    }

}
