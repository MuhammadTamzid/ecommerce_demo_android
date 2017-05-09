package com.pastime.avishek.e_commercedemo.util;

import timber.log.Timber;

/**
 * Created by Avishek on 4/18/17.
 */

public class T {
    public static void e(Throwable error) {
        Timber.e(error);
    }

    public static void e(Throwable error, String message) {
        Timber.e(error, message);
    }
}
