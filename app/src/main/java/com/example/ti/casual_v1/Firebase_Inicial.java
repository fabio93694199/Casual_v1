package com.example.ti.casual_v1;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by ti on 28/09/16.
 */

public class Firebase_Inicial extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
