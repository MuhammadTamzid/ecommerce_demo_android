package com.pastime.avishek.e_commercedemo.util;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.activity.BaseActivity;
import com.pastime.avishek.e_commercedemo.fragment.HomeFragment;
import com.pastime.avishek.e_commercedemo.fragment.ProductFragment;
import com.pastime.avishek.e_commercedemo.interfaces.NavigationManager;
import com.pastime.avishek.e_commercedemo.model.MovieModel;

import static com.pastime.avishek.e_commercedemo.constants.GlobalConstants.KEY_MESSAGE;

/**
 * Created by Avishek on 5/9/17.
 */

public class Navigator implements NavigationManager {
    private static Navigator sInstance;

    private BaseActivity mActivity;
    private FragmentManager mFragmentManager;

    public static Navigator getInstance(BaseActivity activity) {
        if (sInstance == null) {
            sInstance = new Navigator();
        }

        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(BaseActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getFragmentManager();
    }

    @Override
    public void navigateToHomeFragment(String value) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_MESSAGE, value);
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(bundle);
        mFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        replaceFragment(R.id.main_content_frame, homeFragment, null);
    }

    @Override
    public void navigateToProductDetailsFragment(MovieModel movieModel) {
        ProductFragment productFragment = new ProductFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(productFragment.getFragmentTag(), movieModel);
        productFragment.setArguments(bundle);
        replaceFragment(R.id.main_content_frame, productFragment, productFragment
                .getFragmentTag
                        ());
    }

    /**
     * Adds a {@link Fragment} to the activity's layout.
     *
     * @param containerViewId The ID of the container view where the fragment is being added to.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Replaces a {@link Fragment} in the activity's layout.
     *
     * @param containerViewId The ID of the container view where the fragment is being added to.
     * @param fragment        The fragment to be added.
     * @param tag             Tag of the fragment to be added to back stack.
     */
    public void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        try {
            fragmentTransaction.setCustomAnimations(
                    R.animator.card_flip_right_in,
                    R.animator.card_flip_right_out,
                    R.animator.card_flip_left_in,
                    R.animator.card_flip_left_out);
            fragmentTransaction.replace(containerViewId, fragment);
            if (tag != null) {
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        } catch (Exception e) {
            T.e(e);
        }
    }
}
