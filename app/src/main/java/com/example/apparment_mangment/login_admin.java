package com.example.apparment_mangment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class login_admin extends AppCompatActivity {

    final ArrayList<String> residentList = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        Intent intent = getIntent();
        final String userUID= intent.getStringExtra("user");
        TextView temp = findViewById(R.id.textview_admin);
        temp.setText("Welcome Admin");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users");

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Resident res = new Resident(
                                ds.child("name").getValue(String.class),
                                ds.child("lastName").getValue(String.class),
                                ds.child("monthsPayed").getValue(Integer.class),
                                ds.child("monthsToPay").getValue(Integer.class)
                        );

                        residentList.add(res.toString());
                }

                display();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        uidRef.addListenerForSingleValueEvent(valueEventListener);
    }

    public void display(){
        ListView listView = findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<>(this, R.layout.activity_login_admin,R.id.textview_admin,residentList);
        listView.setAdapter(arrayAdapter);
    }
}
