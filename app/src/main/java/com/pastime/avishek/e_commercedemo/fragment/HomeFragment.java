package com.pastime.avishek.e_commercedemo.fragment;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.adapter.GridAdapter;
import com.pastime.avishek.e_commercedemo.interfaces.FragmentCommunicator;
import com.pastime.avishek.e_commercedemo.model.MovieModel;
import com.pastime.avishek.e_commercedemo.util.GridViewDataSource;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.pastime.avishek.e_commercedemo.constants.GlobalConstants.KEY_MESSAGE;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.product_grid)
    GridView productGridView;
    @BindView(R.id.text_home_title)
    TextView titleTextView;

    private View mLayout;
    private ArrayList<MovieModel> mMovieModels;
    private FragmentCommunicator mFragmentCommunicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayout = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mLayout);

        return mLayout;
    }

    private void animateTitle() {
        titleTextView.setVisibility(View.VISIBLE);
        YoYo.with(Techniques.FadeOut)
                .duration(getActivity().getResources().getInteger(R.integer
                        .title_fragment_home_animation_duration))
                .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                .interpolate(new AccelerateDecelerateInterpolator())
                .withListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        titleTextView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                })
                .playOn(titleTextView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mFragmentCommunicator = (FragmentCommunicator) getActivity();
        getDataFromActivity();
        getGridViewData();
        animateTitle();
    }

    private void getDataFromActivity() {
        titleTextView.setText(getArguments().getString(KEY_MESSAGE));
    }

    private void getGridViewData() {
        mMovieModels = GridViewDataSource.getData();
        GridAdapter gridAdapter = new GridAdapter(getActivity().getApplicationContext());
        gridAdapter.setMovieModels(mMovieModels);
        productGridView.setAdapter(gridAdapter);
        productGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mFragmentCommunicator != null) {
                    mFragmentCommunicator.onFragmentResponse(mMovieModels.get(position));
                }
            }
        });
    }
}
