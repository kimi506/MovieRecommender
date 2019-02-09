package students.com.movierecommender.view.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.view.adapter.ActorAdapter;
import java.util.ArrayList;
import java.util.List;

public class ActorsFragment extends Fragment {

    public static Fragment newInstance(List<Actor> actors)
    {
        ActorsFragment actorsFragment = new ActorsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("actors", new ArrayList<>(actors));
        actorsFragment.setArguments(bundle);
        return actorsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_actors, container, false);
        ListView listView = rootView.findViewById(R.id.list);
        ActorAdapter adapter = new ActorAdapter((ArrayList<Actor>) getArguments().getSerializable("actors"), getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        return rootView;
    }
}
