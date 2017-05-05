package com.pastime.avishek.e_commercedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.model.MovieModel;
import com.pastime.avishek.e_commercedemo.util.ToggleListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noor_framgia on 4/27/17.
 */

public class GridAdapter extends BaseAdapter {

    private final Context mContext;
    private ArrayList<MovieModel> mMovieModels;

    public ArrayList<MovieModel> getMovieModels() {
        return mMovieModels;
    }

    public void setMovieModels(ArrayList<MovieModel> movieModels) {
        this.mMovieModels = movieModels;
    }

    public GridAdapter(Context context) {
        mContext = context;
        mMovieModels = new ArrayList<MovieModel>();
    }

    @Override
    public int getCount() {
        return mMovieModels.size();
    }

    @Override
    public MovieModel getItem(int position) {
        return mMovieModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.cell_grid_product, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mMovieModels.get(position).getName());
        Picasso.with(mContext)
                .load(mMovieModels.get(position).getImageUrl())
                .into(viewHolder.imageView);
        viewHolder.imageWish.setOnClickListener(new ToggleListener() {
            @Override
            public void onToggle(boolean isOn) {
                if (isOn) {
                    viewHolder.imageWish.setImageDrawable(mContext.getResources()
                            .getDrawable(R
                                    .drawable.ic_wishlist_selected_black));
                    animateView(viewHolder.imageWish);
                } else {
                    viewHolder.imageWish.setImageDrawable(mContext.getResources().getDrawable
                            (R.drawable.ic_wishlist_deselected_black));
                    animateView(viewHolder.imageWish);
                }

            }
        });
        return convertView;
    }

    private void animateView(final View view) {
        YoYo.with(Techniques.Landing)
                .duration(mContext.getResources().getInteger(R.integer
                        .wish_icon_animation_duration))
                .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                .interpolate(new AccelerateDecelerateInterpolator())
                .playOn(view);
    }

    public class ViewHolder {
        @BindView(R.id.image_cell_grid_product)
        ImageView imageView;

        @BindView(R.id.text_movie_product)
        TextView textView;

        @BindView(R.id.image_wish)
        ImageView imageWish;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
