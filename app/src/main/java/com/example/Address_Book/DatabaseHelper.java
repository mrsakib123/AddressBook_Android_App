package com.example.Address_Book;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DatabaseName = "AddressBookdb.db";
    private static final String TableName = "User_detailsdb";
    private static final String ID = "id";
    private static final String Name = "name";
    private static final String Gmail = "gmail";
    private static final String Age = "age";
    private static final String MobileNumber = "mobileNum";
    private static final String CurrentAddress = "CurrAddress";
    private static final int VersionNumber = 1;
    private static final String CreateTable = "CREATE TABLE "+TableName+" ("+ID+" INTEGER PRIMARY KEY , "+Name+" VARCHAR(30),"+Gmail+" VARCHAR(30),"+Age+" VARCHAR(30),"+MobileNumber+" VARCHAR(30),"+CurrentAddress+" VARCHAR(30))";
    private Context context;


    public DatabaseHelper(@Nullable Context context) {
        super(context, DatabaseName, null, VersionNumber);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        try {
            sqLiteDatabase.execSQL(CreateTable);
            Toast.makeText(context, "onCreate is called", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        try{
            Toast.makeText(context, "onUpgrade is called", Toast.LENGTH_SHORT).show();
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName);
            onCreate(sqLiteDatabase);
        }catch (Exception e){
            Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
        }

    }

    public long saveData(String id, String name, String gmail, String age, String mobileNum, String curAddress){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Name, name);
        contentValues.put(Gmail, gmail);
        contentValues.put(Age, age);
        contentValues.put(MobileNumber, mobileNum);
        contentValues.put(CurrentAddress, curAddress);



        long rowNumber = sqLiteDatabase.insert(TableName,null,contentValues);
        return rowNumber;

    }

    public Cursor showAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TableName,null);
        return cursor;
    }
    /*public Cursor showData(String name){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM '"+TableName+"' WHERE '"+Name+"'=name ",null);
        return cursor;
    }*/

    public Boolean updateData(String id, String name,String age,String mail,String mobile,String address){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(Name, name);
        contentValues.put(Age, age);
        contentValues.put(Gmail, mail);
        contentValues.put(MobileNumber, mobile);
        contentValues.put(CurrentAddress, address);
        sqLiteDatabase.update(TableName,contentValues,ID+" = ?", new String[] {id});
        return true;

    }

    public int deleteData(String id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int value = sqLiteDatabase.delete(TableName, ID+" = ?", new String[] {id});
        return value;

    }

    public Boolean deleteAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName);
        onCreate(sqLiteDatabase);
        return true;

    }

   /* public Boolean deleteAllData() {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TableName);
        return true;

    }*

  /*  public Cursor showData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM '"+TableName+"' WHERE id = '"+ID+"'",null);
        return cursor;
    }*/


}

