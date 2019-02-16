package students.com.movierecommender.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Review;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView userName;
        RatingBar ratingBar;
        TextView reviewText;
    }

    public ReviewAdapter(List<Review> data, Context context) {
        super(context, R.layout.review_item_row, data);
        this.mContext = context;
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Review reviewModel = getItem(position);
        ViewHolder viewHolder;

        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.review_item_row, parent, false);
            viewHolder.userName = convertView.findViewById(R.id.user_name);
            viewHolder.ratingBar = convertView.findViewById(R.id.rating);
            viewHolder.reviewText = convertView.findViewById(R.id.review_text);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.userName.setText(reviewModel.getUser().getName());
        viewHolder.ratingBar.setRating(reviewModel.getRating());
        viewHolder.reviewText.setText(reviewModel.getText());
        return convertView;
    }
}
