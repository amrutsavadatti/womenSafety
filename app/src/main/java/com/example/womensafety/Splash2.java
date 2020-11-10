package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class Splash2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);

        SharedPreferences getShared = getSharedPreferences("userData",MODE_PRIVATE);
        Boolean sp1 = getShared.getBoolean("boolPg_1",false);
        Boolean sp2 = getShared.getBoolean("boolPg_2",false);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(Splash2.this==null){
                    return;
                }
                else if (sp1 && sp2){
                    Intent intentHome = new Intent(getApplicationContext(), BasePage.class);
                    startActivity(intentHome);
                    finish();
                }else if (sp1){
                    //TODO  1) revisitng add contacts feature
                    Intent intentHome = new Intent(getApplicationContext(), emergencyContacts.class);
                    startActivity(intentHome);
                    finish();
                }
                else{
                    Log.d("sp", "sp value: "+ sp1 + sp2);
                    Intent intentHome = new Intent(getApplicationContext(), homePage.class);
                    startActivity(intentHome);
                    finish();
                }

            }
        },2000);

    }
}