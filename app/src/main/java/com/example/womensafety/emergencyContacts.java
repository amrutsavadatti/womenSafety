package com.example.womensafety;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.womensafety.adapter.RecyclerViewAdapter;
import com.example.womensafety.data.MyDBHandler;
import com.example.womensafety.model.Contacts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class emergencyContacts extends AppCompatActivity implements contactDialog.contactListener {

    private TextView contactName;
    private TextView contactPh;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<Contacts> contactsArrayList;
    private ArrayAdapter<String> arrayAdapter;

    MyDBHandler db = new MyDBHandler(emergencyContacts.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);


        // RecyclerView initialization
        recyclerView = findViewById(R.id.rcv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        contactsArrayList = new ArrayList<>();

        // Get all contacts
        List<Contacts> contactsList = db.getAllContacts();
        for (Contacts c : contactsList){
            contactsArrayList.add(c);
            Log.d("print All contact", "ID: "+ c.getId() + "\n" +
                    "Name: "+ c.getName() + "\n");
        }
        // implementing recyclerview
        recyclerViewAdapter = new RecyclerViewAdapter(emergencyContacts.this,contactsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

        FloatingActionButton openDialog = findViewById(R.id.addPpl);
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                MyDBHandler db1 = new MyDBHandler(emergencyContacts.this);

                if (db.getCount() >= 0 && db.getCount() < 5){

                    openDialog();
                }else{

                    Log.d("abc", "onClick: ERROR");
                }
                //openDialog();

            }
        });

        FloatingActionButton goNxt = findViewById(R.id.nxtPage);
        goNxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (db.getCount() > 0 ){

                    Intent intentZ = new Intent(getApplicationContext(), BasePage.class);
                    startActivity(intentZ);
                    finish();

                }else{
                    Toast.makeText(emergencyContacts.this,"Add at least one Contact",Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void openDialog() {
        contactDialog cd = new contactDialog();
        cd.show(getSupportFragmentManager(),"test dialog");

    }

    @Override
    public void applyText(String name, String phNo) {



        //Adding contact
        Contacts one = new Contacts();
        one.setName(name);
        one.setPhoneNumber(phNo);
        db.addContact(one);
        db.close();

        SharedPreferences userSharedPref = getSharedPreferences("userData",MODE_PRIVATE);
        SharedPreferences.Editor editor = userSharedPref.edit();

        editor.putBoolean("boolPg_2",true);
        editor.apply();

        Log.d("dbCheck", "one :" + one.getPhoneNumber());

        contactsArrayList = new ArrayList<>();

        // Get all contacts
        List<Contacts> contactsList = db.getAllContacts();
        for (Contacts c : contactsList){
            contactsArrayList.add(c);
            Log.d("print All contact", "ID: "+ c.getId() + "\n" +
                    "Name: "+ c.getName() + "\n");
        }
        // implementing recyclerview
        recyclerViewAdapter = new RecyclerViewAdapter(emergencyContacts.this,contactsArrayList);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}