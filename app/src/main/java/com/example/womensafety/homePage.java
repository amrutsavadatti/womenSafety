package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class homePage extends AppCompatActivity {

    private EditText userName;
    private EditText userPhone;
    private String ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Setting users name and phoneNumber




        FloatingActionButton goToEmergrncyPage = findViewById(R.id.goToEmer);

        goToEmergrncyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = (EditText)findViewById(R.id.nameInput);
                userPhone = (EditText)findViewById(R.id.userPh);
                MainActivity.userLady = userName.getText().toString();
                ph = userPhone.getText().toString();
                MainActivity.AccountExists = true;

                SharedPreferences userSharedPref = getSharedPreferences("userData",MODE_PRIVATE);
                SharedPreferences.Editor editor = userSharedPref.edit();

                editor.putString("name", MainActivity.userLady);
                editor.putBoolean("boolPg_1",true);
                editor.apply();

                if (MainActivity.userLady.equals("") || ph.equals("")){
                    Toast.makeText(homePage.this,"Add You Name And Phone Number to proceed",Toast.LENGTH_LONG).show();
                }else{
                    Log.d("checkName", "onClick:"+MainActivity.userLady);
                    Intent goToEmergency = new Intent(getApplicationContext(), emergencyContacts.class);
                    startActivity(goToEmergency);
                    finish();
                }

            }
        });
//
//

    }
}