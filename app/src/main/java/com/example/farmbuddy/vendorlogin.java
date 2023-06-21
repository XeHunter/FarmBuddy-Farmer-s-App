package com.example.farmbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class vendorlogin extends AppCompatActivity {
    TextView t1,t2;
    Button b1;
    TextInputLayout cn,e,p;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    static String PREFS_NAME1 = "myPrefFiles1";
    static String USER_NAME1 = "file1";
    static String KEY_NAME1 = "name1";
    SharedPreferences sd ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendorlogin);
        getSupportActionBar().hide();
        t1=findViewById(R.id.signUpPg);
        t2 = findViewById(R.id.fpass);
        e=findViewById(R.id.email);
        p=findViewById(R.id.PassField);
        b1 = findViewById(R.id.login);
        cn = findViewById(R.id.company1);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        sd = getSharedPreferences(USER_NAME1 , MODE_PRIVATE);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String company = cn.getEditText().getText().toString().trim();
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
                                Toast.makeText(vendorlogin.this, "Login SuccessFull!", Toast.LENGTH_SHORT).show();
                                SharedPreferences sharedPreferences = getSharedPreferences(vendorlogin.PREFS_NAME1 , MODE_PRIVATE);
                                SharedPreferences.Editor editor2 = sharedPreferences.edit();
                                editor2.putBoolean("hasLoggedInv" ,true);
                                editor2.commit();
                                SharedPreferences.Editor editor1 = sd.edit();
                                editor1.putString(KEY_NAME1 , company);
                                editor1.apply();
                                Intent i = new Intent(vendorlogin.this,hvendor.class);
                                startActivity(i);
                                finish();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(vendorlogin.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });

            }
        });



        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(vendorlogin.this,signup.class);
                startActivity(i);
                finish();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(vendorlogin.this,fvendor.class);
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(vendorlogin.this, Logins.class);
        startActivity(i);
        finish();
    }
}