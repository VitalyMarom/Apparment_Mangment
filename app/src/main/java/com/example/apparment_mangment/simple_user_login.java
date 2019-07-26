package com.example.apparment_mangment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class simple_user_login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_user_login);

        final ProgressBar progressBar = findViewById(R.id.progressBar);

        Intent intent = getIntent();
        final String userUID= intent.getStringExtra("user");
        //final TextView temp = findViewById(R.id.userTextView);
        //temp.setText(userUID);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //String path = String.format("users/{0}",userUID);
        String path = String.format("users");
        DatabaseReference myRef = database.getReference(path);
        DatabaseReference uidRef = database.getReference();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        //temp.setText(temp.getText().toString() + "\n" + myRef.child("name").toString());

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(userUID) != null)
                {
                    Resident res = new Resident(
                            dataSnapshot.child(userUID).child("name").getValue(String.class),
                            dataSnapshot.child(userUID).child("lastName").getValue(String.class),
                            dataSnapshot.child(userUID).child("monthsPayed").getValue(Integer.class),
                            dataSnapshot.child(userUID).child("monthsToPay").getValue(Integer.class)
                    );
                    progressBar.setVisibility(View.GONE);
                    TextView welcome = findViewById(R.id.user_name);
                    TextView havePaid = findViewById(R.id.user_info_paid);
                    TextView toPay = findViewById(R.id.user_info_needtopay);

                    welcome.setText(welcome.getText().toString() + res.getName() + " " + res.getlastName());
                    havePaid.setText(havePaid.getText().toString() + res.getMonthsPayed() + "months");
                    toPay.setText(toPay.getText().toString() + res.getMonthsToPay() + "months");
                }
                else
                {
                    //temp.setText("Sorry - we have a server problem");
                }
                /*temp.setText(temp.getText().toString() + "\n" + dataSnapshot.getChildrenCount());
                temp.setText(temp.getText().toString() + "\n" + dataSnapshot.toString());
                temp.setText(temp.getText().toString() + "\n" + dataSnapshot.child(userUID).toString());

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    temp.setText(temp.getText().toString() + "\n" + ds.toString());
                    Resident res = new Resident(
                            ds.child("name").getValue(String.class),
                            ds.child("lastName").getValue(String.class),
                            ds.child("monthsPayed").getValue(Integer.class),
                            ds.child("monthsToPay").getValue(Integer.class)
                    );

                    temp.setText(temp.getText().toString() + "\n" + res.toString());
                }
                */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRef.addListenerForSingleValueEvent(valueEventListener);
    }
}
