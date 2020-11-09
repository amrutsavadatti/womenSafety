package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Splash2.this==null){
                    return;
                }
                else if (MainActivity.AccountExists){
                    Intent intentHome = new Intent(getApplicationContext(), BasePage.class);
                    startActivity(intentHome);
                    finish();
                }
                else{
                    Intent intentHome = new Intent(getApplicationContext(), homePage.class);
                    startActivity(intentHome);
                    finish();
                }

            }
        },2000);

    }
}