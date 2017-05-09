package com.pastime.avishek.e_commercedemo.fragment;

import android.app.Fragment;
import android.widget.Toast;

/**
 * Created by Avishek on 4/18/17.
 */

public abstract class BaseFragment extends Fragment {
    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message An string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Returns the tag of a {@link Fragment} to add to back stack.
     *
     * @return {@link String} representing the tag of the fragment.
     */
    public abstract String getFragmentTag();
}
