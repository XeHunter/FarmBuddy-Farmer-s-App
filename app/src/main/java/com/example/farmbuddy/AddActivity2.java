package com.example.farmbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity2 extends AppCompatActivity {
    TextInputLayout c_name,u_email,u_contact,u_location,u_product,u_price,u_curl;
    Button save,cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        getSupportActionBar().hide();
        c_name = findViewById(R.id.c_name1);
        u_email = findViewById(R.id.u_email1);
        u_contact = findViewById(R.id.u_contact1);
        u_location = findViewById(R.id.u_location1);
        u_product = findViewById(R.id.u_product1);
        u_price = findViewById(R.id.u_price1);
        u_curl = findViewById(R.id.curl1);
        save = findViewById(R.id.btnAdd);
        cancel =findViewById(R.id.btnCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c = c_name.getEditText().getText().toString();
                String e = u_email.getEditText().getText().toString();
                String con = u_contact.getEditText().getText().toString();
                String l = u_location.getEditText().getText().toString();
                String pro = u_product.getEditText().getText().toString();
                String pri = u_price.getEditText().getText().toString();
                String curl = u_curl.getEditText().getText().toString();

                if (!c.isEmpty()) {
                    c_name.setError(null);
                    c_name.setErrorEnabled(false);
                    if (!e.isEmpty()) {
                        u_email.setError(null);
                        u_email.setErrorEnabled(false);
                        if (!con.isEmpty()) {
                            u_contact.setError(null);
                            u_contact.setErrorEnabled(false);
                            if (!l.isEmpty()) {
                                u_location.setError(null);
                                u_location.setErrorEnabled(false);
                                if (!pro.isEmpty()) {
                                    u_product.setError(null);
                                    u_product.setErrorEnabled(false);
                                    if (!pri.isEmpty()) {
                                        u_price.setError(null);
                                        u_price.setErrorEnabled(false);
                                        if (!curl.isEmpty()) {
                                            u_curl.setError(null);
                                            u_curl.setErrorEnabled(false);

                                            insertData();

                                        } else {
                                            u_curl.setError("Please enter image url");
                                        }
                                    } else {
                                        u_price.setError("Please enter price");
                                    }
                                } else {
                                    u_product.setError("Please enter product name");
                                }
                            } else {
                                u_location.setError("Please enter location");
                            }
                        } else {
                            u_contact.setError("Please enter contact number");
                        }

                    } else {
                        u_email.setError("Please enter email");
                    }
                } else {
                    c_name.setError("Please enter company name");
                }



            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddActivity2.this,hvendor.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void insertData()
    {
        Map<String,Object> map = new HashMap<>();
        map.put("Company_Name",c_name.getEditText().getText().toString());
        map.put("Email",u_email.getEditText().getText().toString());
        map.put("Contact",u_contact.getEditText().getText().toString());
        map.put("Location",u_location.getEditText().getText().toString());
        map.put("Product",u_product.getEditText().getText().toString());
        map.put("price",u_price.getEditText().getText().toString());
        map.put("curl",u_curl.getEditText().getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Vendor").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity2.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddActivity2.this,hvendor.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddActivity2.this, "Error While Insertion", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}