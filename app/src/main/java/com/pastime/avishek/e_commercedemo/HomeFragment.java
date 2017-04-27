package com.pastime.avishek.e_commercedemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.pastime.avishek.e_commercedemo.adapter.GridAdapter;
import com.pastime.avishek.e_commercedemo.constants.GlobalConstants;
import com.pastime.avishek.e_commercedemo.model.MovieModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.product_grid)
    GridView productGridView;

    private View mLayout;
    private ArrayList<MovieModel> mMovieModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLayout = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, mLayout);

        return mLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getGridViewData();
    }

    private void getGridViewData() {
        mMovieModels = new ArrayList<>(10);

        for (int i = 0; i<10; i++ ){
            mMovieModels.add(new MovieModel("Name " + i, false, "abcd"));
        }
        GridAdapter gridAdapter = new GridAdapter(getActivity().getApplicationContext());
        gridAdapter.setMovieModels(mMovieModels);
        productGridView.setAdapter(gridAdapter);


    }
}
