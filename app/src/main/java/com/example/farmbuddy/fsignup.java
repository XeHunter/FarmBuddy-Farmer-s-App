package com.example.farmbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.google.firebase.firestore.FirebaseFirestore;

public class fsignup extends AppCompatActivity {
    Button b1;
    String[] items ={"Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana",
            "Himachal Pradesh","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram",
            "Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttar Pradesh","Uttarakhand",
            "West Bengal"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    TextView t1;
    TextInputLayout name, num, email, age, state,pass;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fsignup);
        getSupportActionBar().hide();
        b1=findViewById(R.id.register);
        name=findViewById(R.id.sNameField);
        num=findViewById(R.id.sphone);
        email=findViewById(R.id.email);
        age=findViewById(R.id.age);
        state=findViewById(R.id.state1);
        pass=findViewById(R.id.sPassField);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        progressDialog = new ProgressDialog(this);




        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getEditText().getText().toString().trim();
                String number = num.getEditText().getText().toString().trim();
                String eemail = email.getEditText().getText().toString().trim();
                String aage = age.getEditText().getText().toString().trim();
                String statee = state.getEditText().getText().toString().trim();
                String password = pass.getEditText().getText().toString().trim();


                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Thank you for you patience!");
                progressDialog.show();
                firebaseAuth.createUserWithEmailAndPassword(eemail,password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Intent i = new Intent(fsignup.this,flogin.class);
                                startActivity(i);
                                finish();
                                progressDialog.cancel();

                                firebaseFirestore.collection("farmer")
                                        .document(FirebaseAuth.getInstance().getUid())
                                        .set(new farmermodel(fname,number,eemail,aage,statee,password));
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(fsignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                progressDialog.cancel();
                            }
                        });

            }
        });

        t1=findViewById(R.id.backToLog);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(fsignup.this,flogin.class);
                startActivity(i);
            }
        });
        autoCompleteTextView =findViewById(R.id.state);
        adapterItems = new ArrayAdapter<String>(this,R.layout.list_item,items);
        autoCompleteTextView.setAdapter(adapterItems);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item  = adapterView.getItemAtPosition(i).toString();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(fsignup.this, flogin.class);
        startActivity(i);
        finish();
    }
}