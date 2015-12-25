package com.example.akshay.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

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
    // get count
    public int count() {

        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "SELECT * FROM records";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();

        return recordCount;

    }

    //read records
    public List<ObjectRecord> read() {

        List<ObjectRecord> recordsList = new ArrayList<ObjectRecord>();

        String sql = "SELECT * FROM records ORDER BY id DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {

                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                String firstname = cursor.getString(cursor.getColumnIndex("firstname"));
                String email = cursor.getString(cursor.getColumnIndex("email"));

                ObjectRecord objectRecord = new ObjectRecord();
                objectRecord.id = id;
                objectRecord.firstname = firstname;
                objectRecord.email = email;

                recordsList.add(objectRecord);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

}