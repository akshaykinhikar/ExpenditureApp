package com.example.akshay.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by akshay on 23/12/15.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME = "RecordDatabase";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE records " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "email TEXT ) ";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);

        onCreate(db);
    }

}