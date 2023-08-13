package com.example.randomdietgeneratorapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "FeedReader.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY, name TEXT, email TEXT, password TEXT)";

    private static final String SQL_CREATE_USER_FOOD_ENTRIES =
            "CREATE TABLE IF NOT EXISTS userFood (_id INTEGER PRIMARY KEY, userEmail TEXT, foodId INTEGER)";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_USER_FOOD_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean checkUserCredentials(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "email"
        };

        String selection = "email = ? AND password = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query("user", projection, selection, selectionArgs, null, null, null);
        boolean userExists = cursor != null && cursor.getCount() > 0;

        // Close the cursor after use
        if (cursor != null) {
            cursor.close();
        }

        return userExists;
    }

    public String getUserNameByEmail (String email){

        SQLiteDatabase db = this.getReadableDatabase();

        String[] projection = {
                "name"
        };

        String selection = "email = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query("user", projection, selection, selectionArgs, null, null, null);

        String userName = null;

        if (cursor != null && cursor.moveToFirst()){
            int nameColumnIndex = cursor.getColumnIndex("name");
            if (nameColumnIndex != -1) {
                userName = cursor.getString(nameColumnIndex);
            }
        }

        return userName;
    };


}

