package com.nickrout.firebasedb;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by nicholasrout on 2017/01/28.
 */

@IgnoreExtraProperties
public class Presenter {

    // Default constructor required for calls
    // to DataSnapshot.getValue(Presenter.class)
    public Presenter() {
    }

    public Presenter(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String name;
    public String surname;
}
