package com.nickrout.firebasedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nicholasrout on 2017/01/28.
 */

public class UserActivity extends AppCompatActivity {

    private static final String TAG = "UserActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }
}
