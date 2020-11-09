package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.womensafety.data.MyDBHandler;
import com.example.womensafety.model.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class emergencyContacts extends AppCompatActivity implements contactDialog.contactListener {

    private TextView contactName;
    private TextView contactPh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        FloatingActionButton openDialog = findViewById(R.id.addPpl);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDialog();

            }
        });


//        MyDBHandler db = new MyDBHandler(emergencyContacts.this);
//
//        //Adding contact
//        Contacts one = new Contacts();
//        one.setName("amryt");
//        one.setPhoneNumber("12345");
//
//        db.addContact(one);
//
//        Log.d("dbCheck", "one :" + one.getPhoneNumber());
    }

    public void openDialog() {
        contactDialog cd = new contactDialog();
        cd.show(getSupportFragmentManager(),"test dialog");

    }

    @Override
    public void applyText(String name, String phNo) {

        MyDBHandler db = new MyDBHandler(emergencyContacts.this);

        //Adding contact
        Contacts one = new Contacts();
        one.setName(name);
        one.setPhoneNumber(phNo);
        db.addContact(one);
        db.close();

        Log.d("dbCheck", "one :" + one.getPhoneNumber());

        // Get all contacts
        List<Contacts> allContacts = db.getAllContacts();
        for (Contacts c : allContacts){
            Log.d("print All contact", "ID: "+ c.getId() + "\n" +
                    "Name: "+ c.getName() + "\n");
        }

    }
}