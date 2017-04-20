package com.pastime.avishek.e_commercedemo;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by Avishek on 4/19/17.
 */

public class EcommerceApplication extends Application {

    private EcommerceApplication sInstance;

    public Application getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            // TODO example of implementation custom crash reporting solution -  Crashlytics.
//            Fabric.with(this, new Crashlytics());
//            Timber.plant(new CrashReportingTree());
        }
    }
}
