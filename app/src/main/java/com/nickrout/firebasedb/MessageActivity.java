package com.nickrout.firebasedb;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by nicholasrout on 2017/01/28.
 */

public class MessageActivity extends AppCompatActivity {

    private static final String TAG = "MessageActivity";
    private static final String REFERENCE_MESSAGE = "message";

    private AppCompatTextView mMessage;
    private AppCompatEditText mInput;

    private FirebaseDatabase mFirebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        mMessage = (AppCompatTextView) findViewById(R.id.message);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.getReference(REFERENCE_MESSAGE).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Successfully read message value
                String message = dataSnapshot.getValue(String.class);
                mMessage.setText(message);
                Snackbar.make(mMessage, "New message!", Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read message value
                Log.e(TAG, "Failed to read message", databaseError.toException());
            }
        });
        mInput = (AppCompatEditText) findViewById(R.id.input);
        AppCompatButton submit = (AppCompatButton) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(mInput.getText())) {
                    String newMessage = mInput.getText().toString();
                    mFirebaseDatabase.getReference(REFERENCE_MESSAGE).setValue(
                            newMessage,
                            new DatabaseReference.CompletionListener() {
                                @Override
                                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                    if (databaseError != null) {
                                        Snackbar.make(mMessage, databaseError.getMessage(), Snackbar.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
