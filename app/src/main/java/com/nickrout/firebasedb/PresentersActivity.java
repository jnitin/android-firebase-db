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

public class PresentersActivity extends AppCompatActivity {

    private static final String TAG = "PresentersActivity";
    private static final String REFERENCE_PRESENTERS = "presenters";

    private FirebaseDatabase mFirebaseDatabase;

    private AppCompatEditText mName;
    private AppCompatEditText mSurname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presenters);
        mName = (AppCompatEditText) findViewById(R.id.name);
        mSurname = (AppCompatEditText) findViewById(R.id.surname);
        AppCompatButton addPresenter = (AppCompatButton) findViewById(R.id.add_presenter);
        addPresenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPresenter(
                        mName.getText().toString(),
                        mSurname.getText().toString());
            }
        });
        mFirebaseDatabase = FirebaseDatabase.getInstance();
    }

    public void createPresenter(String email, String password) {
        DatabaseReference presentersReference = mFirebaseDatabase.getReference(REFERENCE_PRESENTERS);
        String presenterId = presentersReference.push().getKey();
        Presenter presenter = new Presenter(email, password);
        presentersReference.child(presenterId).setValue(presenter, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    Snackbar.make(mName, "Presenter added!", Snackbar.LENGTH_SHORT).show();
                } else {
                    // Failed to add presenter
                    Log.e(TAG, "Failed to add presenter", databaseError.toException());
                }
            }
        });
    }
}
