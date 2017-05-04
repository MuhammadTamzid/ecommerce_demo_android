package com.pastime.avishek.e_commercedemo.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.model.MovieModel;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.BindView;

/**
 * Created by noor_framgia on 5/4/17.
 */

public class ProductFragment extends BaseFragment {

    @BindView(R.id.image_product)
    ImageView imageView;

    @BindView(R.id.text_product_name)
    TextView textView;

    private View mLayout;

    public String getFragmentTag(){
        return getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, mLayout);

        return mLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getProductData();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getProductData(){
        Bundle bundle = this.getArguments();
        MovieModel movieModel = (MovieModel) bundle.get(getFragmentTag());
        textView.setText(movieModel.getName());
        Picasso.with(getContext())
                .load(movieModel.getImageUrl())
                .into(imageView);
    }
}
