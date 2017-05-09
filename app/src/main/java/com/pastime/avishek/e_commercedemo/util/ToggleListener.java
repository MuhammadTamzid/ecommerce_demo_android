package com.pastime.avishek.e_commercedemo.util;

import android.view.View;

/**
 * Created by Avishek on 5/5/17.
 */

public abstract class ToggleListener implements View.OnClickListener {
    private boolean mToggleState = false;

    public abstract void onToggle(boolean isOn);

    @Override
    public void onClick(View view) {
        mToggleState = !mToggleState;
        onToggle(mToggleState);
    }
}
