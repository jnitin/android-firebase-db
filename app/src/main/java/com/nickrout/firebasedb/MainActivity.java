package com.nickrout.firebasedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String REFERENCE_MESSAGE = "message";

    private TextView mMessage;
    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessage = (TextView) findViewById(R.id.message);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference(REFERENCE_MESSAGE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Successfully read message value
                String message = dataSnapshot.getValue(String.class);
                mMessage.setText(message);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read message value
                Log.e(TAG, "Failed to read message value", databaseError.toException());
            }
        });
    }
}
