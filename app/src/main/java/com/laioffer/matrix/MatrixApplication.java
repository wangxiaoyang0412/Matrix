package com.laioffer.matrix;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MatrixApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }

}

