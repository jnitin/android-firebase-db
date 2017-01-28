package com.nickrout.firebasedb;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nicholasrout on 2017/01/28.
 */

public class FirebaseDBApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
