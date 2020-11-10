package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {
    public static boolean AccountExists;
    public static String userLady;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(MainActivity.this==null){
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), Splash2.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}