package students.com.movierecommender.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Review;
import students.com.movierecommender.view.adapter.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReviewsFragment extends Fragment {

    public static Fragment newInstance(List<Review> reviews)
    {
        ReviewsFragment reviewsFragment = new ReviewsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("reviews", new ArrayList<>(reviews));
        reviewsFragment.setArguments(bundle);
        return reviewsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reviews, container, false);
        ListView listView = rootView.findViewById(R.id.review_list);
        ArrayList<Review> reviews = (ArrayList<Review>) getArguments().getSerializable("reviews");
        ReviewAdapter adapter = new ReviewAdapter(reviews, getActivity().getApplicationContext());
        listView.setAdapter(adapter);
        return rootView;
    }
}
