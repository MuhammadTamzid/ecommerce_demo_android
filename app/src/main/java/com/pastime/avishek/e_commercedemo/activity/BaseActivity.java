package com.pastime.avishek.e_commercedemo.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.util.L;

import timber.log.Timber;

/**
 * Created by Avishek on 4/18/17.
 */

public abstract class BaseActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Replaces a {@link Fragment} in this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment        The fragment to be added.
     * @param tag         Tag of the fragment to be added to back stack.
     */
    public void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        try {
            fragmentTransaction.replace(containerViewId, fragment);
            if (tag != null) {
                fragmentTransaction.addToBackStack(tag);
            }
            fragmentTransaction.commit();
            this.getFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            L.e(this, e.toString());
        }
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
