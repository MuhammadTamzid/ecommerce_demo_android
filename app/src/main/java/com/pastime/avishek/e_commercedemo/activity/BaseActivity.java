package com.pastime.avishek.e_commercedemo.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pastime.avishek.e_commercedemo.util.L;

/**
 * Created by Avishek on 4/18/17.
 */

public class BaseActivity extends AppCompatActivity {

    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentTransaction = this.getFragmentManager().beginTransaction();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        mFragmentTransaction.add(containerViewId, fragment);
        mFragmentTransaction.commit();
    }

    /**
     * Replaces a {@link Fragment} in this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     * @param isStack Boolean flag determining whether fragment is to be added to back stack.
     */
    public void replaceFragment(int containerViewId, Fragment fragment, boolean isStack) {
        try {
            mFragmentTransaction.replace(containerViewId, fragment);
            if (isStack) {
                mFragmentTransaction.addToBackStack(null);
            }
            if (mFragmentTransaction != null)
                mFragmentTransaction.commit();
        } catch (Exception e) {
            L.e(this, e.toString());
        }
    }
}
