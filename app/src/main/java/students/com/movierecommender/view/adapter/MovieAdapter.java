package students.com.movierecommender.view.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import students.com.movierecommender.R;
import students.com.movierecommender.data.entity.Movie;

import java.util.List;


/**
 * Created by db on 09/02/19.
 */
public class MovieAdapter extends ArrayAdapter<Movie> implements View.OnClickListener {

    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtName;
        RatingBar ratingBar;
        ImageView frontImage;
        ImageView info;
    }

    public MovieAdapter(List<Movie> data, Context context) {
        super(context, R.layout.movie_item_row, data);
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        Movie movieModel = getItem(position);

        switch (v.getId()) {
            case R.id.item_info:
                Snackbar.make(v, "Type: " + movieModel.getMovieTypes() +
                        "\nProduction year: " + movieModel.getProductionYear(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Movie movieModel = getItem(position);
        ViewHolder viewHolder;

        final View result;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.movie_item_row, parent, false);
            viewHolder.txtName = convertView.findViewById(R.id.name);
            viewHolder.ratingBar = convertView.findViewById(R.id.rating);
            viewHolder.frontImage = convertView.findViewById(R.id.front_image);
            viewHolder.info = convertView.findViewById(R.id.item_info);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        Bitmap bmp = BitmapFactory.decodeByteArray(movieModel.getFrontImage(), 0, movieModel.getFrontImage().length);

        viewHolder.txtName.setText(movieModel.getName());
        viewHolder.ratingBar.setRating(movieModel.getRating());
        viewHolder.frontImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                bmp.getHeight(), false));
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        return convertView;
    }
}
