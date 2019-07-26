package com.example.apparment_mangment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText user_email = findViewById(R.id.email);
        final EditText user_password = findViewById(R.id.password);
        final Button login = findViewById(R.id.login);
        final Button register = findViewById(R.id.register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        final TextView test = findViewById(R.id.textView);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = user_email.getText().toString();
                String password = user_password.getText().toString();

                final Intent intent;

                if(email.equals("admin@a.com"))
                {
                    intent = new Intent(MainActivity.this, login_admin.class);
                }
                else
                {
                    intent = new Intent(MainActivity.this, simple_user_login.class);
                }

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("login", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    intent.putExtra("user",user.getUid());
                                    startActivity(intent);

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("login", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user_email.getText().toString();
                String password = user_password.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("register", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("register", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });
            }
        });
    }
}
