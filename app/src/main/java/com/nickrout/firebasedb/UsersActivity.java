package com.nickrout.firebasedb;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by nicholasrout on 2017/01/28.
 */

public class UsersActivity extends AppCompatActivity {

    private static final String TAG = "UsersActivity";
    private static final String REFERENCE_USERS = "users";

    private FirebaseDatabase mFirebaseDatabase;

    private AppCompatEditText mEmail;
    private AppCompatEditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        mEmail = (AppCompatEditText) findViewById(R.id.email);
        mPassword = (AppCompatEditText) findViewById(R.id.password);
        AppCompatButton addUser = (AppCompatButton) findViewById(R.id.add_user);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser(
                        mEmail.getText().toString(),
                        mPassword.getText().toString());
            }
        });
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void createUser(String email, String password) {
        DatabaseReference usersReference = mFirebaseDatabase.getReference(REFERENCE_USERS);
        String userId = usersReference.push().getKey();
        User user = new User(email, password);
        usersReference.child(userId).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Snackbar.make(mEmail, "User added!", Snackbar.LENGTH_SHORT).show();
                } else {
                    // Failed to add user
                    Log.e(TAG, "Failed to add user", databaseError.toException());
                }
            }
        });
    }
}
