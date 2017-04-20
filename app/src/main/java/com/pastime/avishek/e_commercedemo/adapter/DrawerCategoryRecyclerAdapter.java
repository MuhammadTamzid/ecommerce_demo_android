package com.pastime.avishek.e_commercedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Avishek on 4/19/17.
 */

public class DrawerCategoryRecyclerAdapter extends RecyclerView
        .Adapter<DrawerCategoryRecyclerAdapter.DrawerRecyclerViewHolder> {

    private final ArrayList<String> mCategories;
    private LayoutInflater mLayoutInflater;
    private Context mContext;

    public DrawerCategoryRecyclerAdapter(Context context, ArrayList<String> categories) {
        this.mContext = context;
        this.mCategories = categories;
    }

    @Override
    public DrawerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mLayoutInflater == null) {
            mLayoutInflater = LayoutInflater.from(parent.getContext());
        }
        View view = mLayoutInflater.inflate(R.layout.row_drawer_category, parent, false);
        return new DrawerRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerRecyclerViewHolder holder, int position) {
        DrawerRecyclerViewHolder drawerRecyclerViewHolder = (DrawerRecyclerViewHolder) holder;
        drawerRecyclerViewHolder.bindContent(mCategories.get(position));
    }

    @Override
    public int getItemCount() {
        return mCategories.size();
    }

    @Override
    public void onViewAttachedToWindow(DrawerRecyclerViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        setAnimation(((DrawerRecyclerViewHolder) holder).getLayout());
    }

    private void setAnimation(View viewToAnimate) {
        Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
        viewToAnimate.startAnimation(animation);
    }

    public class DrawerRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_drawer_category)
        TextView textViewCategory;
        @BindView(R.id.image_drawer_category_indicator)
        ImageView subMenuIndicator;
        @BindView(R.id.view_drawer_category_divider)
        View divider;
        @BindView(R.id.linear_drawer_row)
        LinearLayout layout;

        public DrawerRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            /*textViewCategory = (TextView) itemView.findViewById(R.id.text_drawer_category);
            subMenuIndicator = (ImageView) itemView.findViewById(R.id
            .image_drawer_category_indicator);
            divider = itemView.findViewById(R.id.view_drawer_category_divider);*/
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: by April, 2017
                }
            });
        }

        public void bindContent(String category) {
            textViewCategory.setText(category);
        }

        public LinearLayout getLayout() {
            return layout;
        }
    }
}
