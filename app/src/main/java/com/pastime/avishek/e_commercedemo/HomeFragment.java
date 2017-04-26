package com.pastime.avishek.e_commercedemo;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pastime.avishek.e_commercedemo.constants.GlobalConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.text_baal_saal)
    TextView textViewBaalSaal;

    private View mLayout;

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
        getArgumentsFromActivity();
    }

    private void getArgumentsFromActivity() {
        String message = getArguments().getString(GlobalConstants.EXTRA_MESSAGE);
        if (message != null) {
            textViewBaalSaal.setText(message);
        }
    }
}
