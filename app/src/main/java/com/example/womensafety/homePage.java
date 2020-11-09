package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class homePage extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Setting users name and phoneNumber

        final EditText userName = (EditText)findViewById(R.id.nameInput);
        final EditText userPhone = (EditText)findViewById(R.id.userPh);
        MainActivity.userLady = userName.toString();


        FloatingActionButton goToEmergrncyPage = findViewById(R.id.goToEmer);

        goToEmergrncyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("checkName", "onClick: "+ MainActivity.userLady + "ph ;" + userPhone);
                Intent goToEmergency = new Intent(getApplicationContext(), emergencyContacts.class);
                startActivity(goToEmergency);
            }
        });
    }
}