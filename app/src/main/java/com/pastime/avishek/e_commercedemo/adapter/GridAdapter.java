package com.pastime.avishek.e_commercedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.model.MovieModel;

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
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.cell_grid_movie, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(mMovieModels.get(position).getName());
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.image_cell_grid_movie)
        ImageView imageView;

        @BindView(R.id.text_movie_name)
        TextView textView;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }


    }
}
