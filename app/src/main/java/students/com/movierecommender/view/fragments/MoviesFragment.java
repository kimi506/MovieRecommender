package students.com.movierecommender.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ListView;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Movie;
import students.com.movierecommender.view.SingleMovieActivity;
import students.com.movierecommender.view.adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MoviesFragment extends Fragment {

    public static Fragment newInstance(List<Movie> movies)
    {
        MoviesFragment moviesFragment = new MoviesFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("movies", new ArrayList<>(movies));
        moviesFragment.setArguments(bundle);
        return moviesFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);
        ListView listView = rootView.findViewById(R.id.list);
        ArrayList<Movie> movies = (ArrayList<Movie>) getArguments().getSerializable("movies");
        MovieAdapter adapter = new MovieAdapter(movies, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Movie movieModel = movies.get(position);

                Intent intent = new Intent(getActivity(), SingleMovieActivity.class);
                intent.putExtra("movieId", movieModel.getId().toString());
                startActivity(intent);
            }
        });
        return rootView;
    }
}
