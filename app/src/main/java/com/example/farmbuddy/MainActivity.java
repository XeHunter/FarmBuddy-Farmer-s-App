package com.example.farmbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td = new Thread(){

            @Override
            public void run() {
                try {


                    sleep(3000);

                }catch (Exception exception){

                    exception.printStackTrace();
                }finally {

                    Intent intent = new Intent(MainActivity.this , main2.class);
                    startActivity(intent);
                    finish();

                }
            }
        };td.start();
    }
}