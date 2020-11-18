package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BasePage extends AppCompatActivity {

    private TextView userName;
    public static Boolean edit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base_page);

        SharedPreferences getShared = getSharedPreferences("userData", MODE_PRIVATE);
        String name = getShared.getString("name", "DefaultName");

        userName = findViewById(R.id.uName);
        userName.setText(name);

        Log.d("name_test", "name is " + name);


        setContentView(R.layout.activity_base_page);

//        Button mButton = (Button) findViewById(R.id.);
//        mButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(BasePage.this, emergencyContacts.class));
//            }
//        });

        FloatingActionButton gohome = findViewById(R.id.gotohomepg);
        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit=true;

                Intent gotohomepg = new Intent(getApplicationContext(),homePage.class);
                startActivity(gotohomepg);
                finish();
            }

        });


        FloatingActionButton goToContacts = findViewById(R.id.gotocont);
        goToContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent gotoEmerContactpg = new Intent(getApplicationContext(), emergencyContacts.class);
                startActivity(gotoEmerContactpg);
                finish();
            }

        });


    }

}
