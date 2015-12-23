package com.example.akshay.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by akshay on 23/12/15.
 */
public class TableControllerRecords extends DatabaseHandler {

    public TableControllerRecords(Context context) {
        super(context);


    }

    public boolean create(ObjectRecord objectRecord) {

        ContentValues values = new ContentValues();

        values.put("firstname", objectRecord.firstname);
        values.put("email", objectRecord.email);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean createSuccessful = db.insert("records", null, values) > 0;
        db.close();

        return createSuccessful;
    }

    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM records";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

}