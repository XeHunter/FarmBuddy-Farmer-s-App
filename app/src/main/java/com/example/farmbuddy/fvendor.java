package com.example.farmbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class fvendor extends AppCompatActivity {
    TextView t1;
    TextInputLayout e;
    Button b;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fvendor);
        getSupportActionBar().hide();
        t1=findViewById(R.id.bvlogin);
        e=findViewById(R.id.email);
        b=findViewById(R.id.reset);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e.getEditText().getText().toString().trim();
                progressDialog.setTitle("Sending Mail");
                progressDialog.setMessage("Thank you for you patience!");
                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(email)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                progressDialog.cancel();
                                Intent i = new Intent(fvendor.this,vendorlogin.class);
                                startActivity(i);
                                finish();
                                Toast.makeText(fvendor.this, "Email Sent!", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(fvendor.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(fvendor.this,vendorlogin.class);
                startActivity(i);
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(fvendor.this, vendorlogin.class);
        startActivity(i);
        finish();
    }
}