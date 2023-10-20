package com.example.ucare;

import android.app.Application;
import android.content.Context;

public class Medicine_remainder extends Application {
    private static Context mInstance;


    @Override
    public void onCreate() {
        super.onCreate();
        if (mInstance == null) {
            mInstance = getApplicationContext();
        }
    }

    public static Context getInstance() {
        return mInstance;
    }
}
