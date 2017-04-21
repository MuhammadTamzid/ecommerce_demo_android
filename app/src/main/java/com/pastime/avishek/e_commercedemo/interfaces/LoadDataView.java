package com.pastime.avishek.e_commercedemo.interfaces;

/**
 * Created by Avishek on 4/24/17.
 */

import android.content.Context;

/**
 * Interface representing a View that will be used to load data.
 */
public interface LoadDataView {
    /**
     * Shows a view with a progress bar indicating a loading process.
     */
    void showLoading();

    /**
     * Hides a loading view.
     */
    void hideLoading();

    /**
     * Shows a retry view in case of an error when retrieving data.
     */
    void showRetry();

    /**
     * Hides a retry view shown if there was an error when retrieving data.
     */
    void hideRetry();

    /**
     * Shows an error message
     *
     * @param message A string representing an error.
     */
    void showError(String message);

    /**
     * Gets a {@link android.content.Context}.
     */
    Context context();
}
