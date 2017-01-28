package com.nickrout.firebasedb;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by nicholasrout on 2017/01/28.
 */

@IgnoreExtraProperties
public class User {

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String email;
    public String password;
}
