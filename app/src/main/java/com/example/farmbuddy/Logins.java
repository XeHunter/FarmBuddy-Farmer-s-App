package com.example.farmbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class Logins extends AppCompatActivity {
    LinearLayout l1,l2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        getSupportActionBar().hide();
        l1=findViewById(R.id.l1);
        l2=findViewById(R.id.l2);
        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(flogin.PREFS_NAME , MODE_PRIVATE);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn" ,false);

                Intent intent;
                if(hasLoggedIn)
                {
                    intent = new Intent(Logins.this, farm.class);
                }else
                {
                    intent = new Intent(Logins.this, flogin.class);
                }
                startActivity(intent);
                finish();
            }
        });
        l2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(vendorlogin.PREFS_NAME1 , MODE_PRIVATE);
                boolean hasLoggedInv = sharedPreferences.getBoolean("hasLoggedInv" ,false);

                Intent i;
                if(hasLoggedInv)
                {
                    i = new Intent(Logins.this, hvendor.class);
                }else
                {
                    i = new Intent(Logins.this, vendorlogin.class);
                }
                startActivity(i);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(Logins.this, main2.class);
        startActivity(i);
        finish();
    }
}