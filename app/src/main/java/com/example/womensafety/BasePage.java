package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class BasePage extends AppCompatActivity {

    private TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_page);

        SharedPreferences getShared = getSharedPreferences("userData",MODE_PRIVATE);
        String name = getShared.getString("name","DefaultName");

        userName = findViewById(R.id.uName);
        userName.setText(name);



    }
}