package com.example.randomdietgeneratorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Singup.db", null, 1);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

       db.execSQL("create table AllUsers(Email TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists AllUsers");

    }

    public Boolean insertData(String email, String password){
        SQLiteDatabase myDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDatabase.insert("AllUsers", null, contentValues);

        return result != -1;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor cursor = myDatabase.rawQuery("select * from AllUserts where email =? " , new String[] {email});
        return cursor.getCount()>0;
    }



}
