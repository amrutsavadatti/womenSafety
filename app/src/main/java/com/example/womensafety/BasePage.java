package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

//location imprts
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.womensafety.data.MyDBHandler;
import com.example.womensafety.model.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BasePage extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private TextView userName;
    public static Boolean edit = false;
    String longitudePublic;
    String latitudePublic;
    private ArrayList<Contacts> contactsArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_page);

//        Asking for SMS permission
        ActivityCompat.requestPermissions(BasePage.this,new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

//        Getting inApp permission for location and internet
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e){
            e.printStackTrace();
        }



        SharedPreferences getShared = getSharedPreferences("userData", MODE_PRIVATE);
        String name = getShared.getString("name", "DefaultName");

        userName = findViewById(R.id.uName);
        userName.setText(name);

        Log.d("name_test", "name is " + name);

        MyDBHandler db = new MyDBHandler(BasePage.this);

        contactsArrayList = new ArrayList<>();





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


        FloatingActionButton Sos = findViewById(R.id.SOS);
        Sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getLocation();

                // Get all contacts
                List<Contacts> contactsList = db.getAllContacts();
                for (Contacts c : contactsList){
                    contactsArrayList.add(c);
                    sendMsg(c.getPhoneNumber() ,longitudePublic,latitudePublic);

                }



            }

        });

    }

    public void getLocation(){
        gpsTracker = new GpsTracker(BasePage.this);
        if(gpsTracker.canGetLocation()){
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            latitudePublic =String.valueOf(latitude);
            longitudePublic = String.valueOf(longitude);
            Log.d("Location", "getLocation: long = "+longitudePublic+" lat = "+latitudePublic);
        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    public void sendMsg(String number, String longitude,String latitude ) {
        Toast.makeText(BasePage.this, "sms sent", Toast.LENGTH_SHORT).show();

        String message = "Save Me!!! I'm at http://maps.google.com/?q=" + latitude + "," + longitude;
//        String number = "9869580141";
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }

}