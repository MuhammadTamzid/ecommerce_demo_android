package com.pastime.avishek.e_commercedemo.fragment;

import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Avishek on 4/18/17.
 */

public class BaseFragment extends Fragment {
    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
