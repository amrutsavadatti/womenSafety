package com.example.womensafety.data;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.womensafety.contactDialog;
import com.example.womensafety.model.Contacts;
import com.example.womensafety.params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {


    public MyDBHandler (Context context){
        super(context, Params.DB_NAME, null,Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + Params.TABLE_NAME + "("
                + Params.KEY_ID + " INTEGER PRIMARY KEY," + Params.KEY_NAME
                + " TEXT, " + Params.KEY_PHONE + " TEXT" + ")";

        Log.d("DbWomen", "onCreate: RUNNING");
        db.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

    public void addContact(Contacts contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        db.insert(Params.TABLE_NAME,null,values);
        Log.d("Insert", "Successfully Inserted ");
        db.close();


    }

    public List<Contacts> getAllContacts(){
        List<Contacts> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Generate the querry to read from the database
        String select = "SELECT * FROM "+ Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(select,null);

        //Loop through now
        if(cursor.moveToFirst()){
            do{
                Contacts c = new Contacts();
                c.setId(Integer.parseInt(cursor.getString(0)));
                c.setName(cursor.getString(1));
                c.setPhoneNumber(cursor.getString(2));
                contactList.add(c);
            }while(cursor.moveToNext());
        }
        return contactList;
        
    }
}
