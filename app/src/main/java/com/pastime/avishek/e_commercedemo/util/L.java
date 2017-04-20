package com.pastime.avishek.e_commercedemo.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by Avishek on 4/18/17.
 */

public class L {
    public static void e(Context context, String message) {
        Log.e(context.getClass().getSimpleName(), message);
    }
}
