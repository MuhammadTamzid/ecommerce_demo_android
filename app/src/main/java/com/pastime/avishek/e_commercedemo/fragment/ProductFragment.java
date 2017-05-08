package com.pastime.avishek.e_commercedemo.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.interfaces.LoadDataView;
import com.pastime.avishek.e_commercedemo.model.MovieModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by noor_framgia on 5/4/17.
 */

public class ProductFragment extends BaseFragment implements View.OnClickListener, LoadDataView {

    @BindView(R.id.image_product)
    ImageView imageView;

    @BindView(R.id.text_product_name)
    TextView textView;

    @BindView(R.id.image_share)
    ImageButton imageButtonShare;

    @BindView(R.id.button_buy)
    Button buttonBuy;

    @BindView(R.id.relative_progress)
    RelativeLayout progressLayout;

    @BindView(R.id.relative_retry)
    RelativeLayout retryLayout;

    private View mLayout;

    @Override
    public String getFragmentTag() {
        return getClass().getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, mLayout);

        return mLayout;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getProductData();
        imageButtonShare.setOnClickListener(this);
        buttonBuy.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void getProductData() {
        Bundle bundle = this.getArguments();
        MovieModel movieModel = (MovieModel) bundle.get(getFragmentTag());
        textView.setText(movieModel.getName());
        Picasso.with(getContext())
                .load(movieModel.getImageUrl())
                .into(imageView);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_share:
            case R.id.button_buy:
                showToastMessage(getActivity().getResources().getString(R.string
                        .message_future_implementation));
        }
    }

    @Override
    public void showLoading() {
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressLayout.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        retryLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        retryLayout.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }
}
