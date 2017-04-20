package com.pastime.avishek.e_commercedemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.adapter.DrawerCategoryRecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Avishek on 4/19/17.
 */

public class DrawerFragment extends BaseFragment {

    @BindView(R.id.recycler_drawer_category)
    RecyclerView recyclerViewCategory;

    private ArrayList<String> mCategories;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle
            savedInstanceState) {

        View layout = inflater.inflate(R.layout.fragment_drawer, container, false);
        ButterKnife.bind(this, layout);
        init();
        return layout;
    }

    private void init() {
        mCategories = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array
                .film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        mCategories.addAll(Arrays.asList(getResources().getStringArray(R.array.film_genre)));
        DrawerCategoryRecyclerAdapter adapter = new DrawerCategoryRecyclerAdapter(getActivity(),
                mCategories);
        recyclerViewCategory.setAdapter(adapter);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
