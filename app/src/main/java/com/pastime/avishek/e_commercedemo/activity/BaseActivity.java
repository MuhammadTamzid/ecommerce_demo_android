package com.pastime.avishek.e_commercedemo.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.pastime.avishek.e_commercedemo.R;
import com.pastime.avishek.e_commercedemo.util.L;

import permissions.dispatcher.PermissionRequest;

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
     * @param tag             Tag of the fragment to be added to back stack.
     */
    public void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
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
            this.getFragmentManager().executePendingTransactions();
        } catch (Exception e) {
            L.e(this, e.toString());
        }
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     *
     * @param message A string representing a message to be shown.
     */
    protected void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Shows a {@link android.widget.Toast} message.
     * 
     * @param stringId An integer representing the ID of a string resource
     */
    protected void showToastMessage(int stringId) {
        showToastMessage(getResources().getString(stringId));
    }

    /**
     * Shows a dialog explaining the rationale of the permission(s) required
     * <p>
     *     Buttons:
     *     <ul>
     *         <li>Allow: allows the permission request to proceed</li>
     *         <li>Deny: cancels the permission request</li>
     *     </ul>
     * </p>
     *
     * @param messageResId  Id of the {@link String} resource
     * @param request       Instance of {@link PermissionRequest} interface
     */
    protected void showRationaleDialog(@StringRes int messageResId, final PermissionRequest
            request) {
        new AlertDialog.Builder(this)
                .setPositiveButton(R.string.button_allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton(R.string.button_deny, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(@NonNull DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .setMessage(messageResId)
                .show();
    }

    /**
     * Shows a snackbar explaining the rationale of the permission
     * <p>
     *     Button:
     *     <ul>
     *         <li>OK: allows the permission request to proceed</li>
     *     </ul>
     * </p>
     *
     * @param view View in which the snackbar will be shown
     * @param messageResId ID of the {@link String} resource
     * @param request Instance of the {@link PermissionRequest} interface
     */
    protected void showRationaleSnackbar(View view, @StringRes int messageResId, final PermissionRequest request) {
        Snackbar.make(view, messageResId,
                Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.button_ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        request.proceed();
                    }
                })
                .show();
    }
}
