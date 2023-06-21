package com.example.farmbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class flogin extends AppCompatActivity {
    TextView t1,t2;
    Button b1;
    TextInputLayout n,e,p;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    static String PREFS_NAME = "myPrefFiles";
    static String USER_NAME = "file";
    static String KEY_NAME = "name";
    SharedPreferences sd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flogin);
        getSupportActionBar().hide();
        t1=findViewById(R.id.signUpPg);
        t2 = findViewById(R.id.fpass);
        b1=findViewById(R.id.login);
        n = findViewById(R.id.sNameField1);
        e=findViewById(R.id.femail);
        p=findViewById(R.id.fPassField);
        sd = getSharedPreferences(USER_NAME , MODE_PRIVATE);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = n.getEditText().getText().toString().trim();
                String email = e.getEditText().getText().toString().trim();
                String pass = p.getEditText().getText().toString().trim();
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Thank you for you patience!");
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(email,pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                progressDialog.cancel();
                                Toast.makeText(flogin.this, "Login SuccessFull!", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences(flogin.PREFS_NAME , MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putBoolean("hasLoggedIn" ,true);
                                editor.commit();
                                SharedPreferences.Editor editor1 = sd.edit();
                                editor1.putString(KEY_NAME , name);
                                editor1.apply();
                                Intent i = new Intent(flogin.this,farm.class);
                                startActivity(i);
                                finish();



                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(flogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(flogin.this,fsignup.class);
                startActivity(i);
                finish();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(flogin.this,ffpass.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(flogin.this, Logins.class);
        startActivity(i);
        finish();
    }
}