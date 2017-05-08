package com.pastime.avishek.e_commercedemo.util;

import com.pastime.avishek.e_commercedemo.model.MovieModel;

import java.util.ArrayList;

/**
 * Created by Avishek on 5/5/17.
 */

public class GridViewDataSource {

    private static final ArrayList<String> sUrls;

    static {
        sUrls = new ArrayList<>(10);
        sUrls.add("http://www.abbott.com.ph/images/MEDICALNUTRITION.jpg");
        sUrls.add("http://www.21stcenturyproof.nl/wp-content/uploads/2015/12/products.jpg");
        sUrls.add("http://www.heinzwatties.co" +
                ".nz/var/heinzwatties/storage/images/our-products/298-17-eng-US/Our-Products.jpg");
        sUrls.add("http://www.tresemme.co" +
                ".nz/Images/1853/1853-577428-Product_Pnel_Fetured__New_460x300.png");
        sUrls.add("http://secure.tresemme" +
                ".com/Images/1338/1338-346718-Product_Pnel_Styling_Products_460x300.png");
        sUrls.add("http://www.abbott.com.ph/images/MEDICALNUTRITION.jpg");
        sUrls.add("http://www.21stcenturyproof.nl/wp-content/uploads/2015/12/products.jpg");
        sUrls.add("http://www.heinzwatties.co" +
                ".nz/var/heinzwatties/storage/images/our-products/298-17-eng-US/Our-Products.jpg");
        sUrls.add("http://www.tresemme.co" +
                ".nz/Images/1853/1853-577428-Product_Pnel_Fetured__New_460x300.png");
        sUrls.add("http://secure.tresemme" +
                ".com/Images/1338/1338-346718-Product_Pnel_Styling_Products_460x300.png");
    }

    public static ArrayList<MovieModel> getData() {
        // Simulate network latency of 2 seconds
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<MovieModel> movieModels = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            movieModels.add(new MovieModel("Product " + (i + 1), false, sUrls.get(i)));
        }

        return movieModels;
    }
}
