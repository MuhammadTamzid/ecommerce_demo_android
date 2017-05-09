package com.pastime.avishek.e_commercedemo.interfaces;

import com.pastime.avishek.e_commercedemo.fragment.HomeFragment;
import com.pastime.avishek.e_commercedemo.fragment.ProductFragment;
import com.pastime.avishek.e_commercedemo.model.MovieModel;

/**
 * Created by Avishek on 5/9/17.
 */

public interface NavigationManager {
    /**
     * Shows {@link HomeFragment} on the screen.
     *
     * @param value Any {@link String} value needed to pass to the fragment.
     */
    void navigateToHomeFragment(String value);

    /**
     * Shows {@link ProductFragment} on the screen.
     *
     * @param movieModel    A {@link MovieModel} object passed as an argument to the fragment.
     */
    void navigateToProductDetailsFragment(MovieModel movieModel);
}
