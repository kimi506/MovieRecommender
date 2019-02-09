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
import students.com.movierecommender.data.entity.Actor;
import students.com.movierecommender.data.entity.Movie;

import java.util.List;

public class ActorAdapter extends ArrayAdapter<Actor> implements View.OnClickListener {

    private Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView txtNameAndSurname;
        ImageView image;
        ImageView info;
    }

    public ActorAdapter(List<Actor> data, Context context) {
        super(context, R.layout.actor_item_row, data);
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_info:
                Snackbar.make(v, "Click on actor to get more info", Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Actor actorModel = getItem(position);
        ActorAdapter.ViewHolder viewHolder;

        final View result;
        if (convertView == null) {
            viewHolder = new ActorAdapter.ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.actor_item_row, parent, false);
            viewHolder.txtNameAndSurname = convertView.findViewById(R.id.name_and_surname);
            viewHolder.image = convertView.findViewById(R.id.image);
            viewHolder.info = convertView.findViewById(R.id.item_info);

            result = convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ActorAdapter.ViewHolder) convertView.getTag();
            result = convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        Bitmap bmp = BitmapFactory.decodeByteArray(actorModel.getImage(), 0, actorModel.getImage().length);

        viewHolder.txtNameAndSurname.setText(actorModel.getName() + " " + actorModel.getSurname());
        viewHolder.image.setImageBitmap(Bitmap.createScaledBitmap(bmp, bmp.getWidth(),
                bmp.getHeight(), false));
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        return convertView;
    }
}
