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

        values.put("price", objectRecord.price);
        values.put("note", objectRecord.note);
        values.put("category", objectRecord.categoryOfRecord);
        values.put("date", objectRecord.dateOfRecord);

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
                String price = cursor.getString(cursor.getColumnIndex("price"));
                String note = cursor.getString(cursor.getColumnIndex("note"));
                String category = cursor.getString(cursor.getColumnIndex("category"));
//                Long date = cursor.getString(cursor.getColumnIndex("date"));
                Long date = cursor.getLong(cursor.getColumnIndex("date"));

                ObjectRecord objectRecord = new ObjectRecord();
                objectRecord.id = id;
                objectRecord.price = price;
                objectRecord.note = note;
                objectRecord.categoryOfRecord = category;
                objectRecord.dateOfRecord = date;

                recordsList.add(objectRecord);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return recordsList;
    }

    //read single record
    public ObjectRecord readSingleRecord(int recordId) {

        ObjectRecord objectRecord = null;

        String sql = "SELECT * FROM records WHERE id = " + recordId;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
            String price = cursor.getString(cursor.getColumnIndex("price"));
            String note = cursor.getString(cursor.getColumnIndex("note"));
            String category = cursor.getString(cursor.getColumnIndex("category"));
            Long date = cursor.getLong(cursor.getColumnIndex("date"));

            objectRecord = new ObjectRecord();
            objectRecord.id = id;
            objectRecord.price = price;
            objectRecord.note = note;
            objectRecord.categoryOfRecord = category;
            objectRecord.dateOfRecord = date;


        }

        cursor.close();
        db.close();

        return objectRecord;

    }

    //update Record
    public boolean update(ObjectRecord objectRecord) {

        ContentValues values = new ContentValues();

        values.put("price", objectRecord.price);
        values.put("note", objectRecord.note);
        values.put("category", objectRecord.categoryOfRecord);
        values.put("date", objectRecord.dateOfRecord);

        String where = "id = ?";

        String[] whereArgs = { Integer.toString(objectRecord.id) };

        SQLiteDatabase db = this.getWritableDatabase();

        boolean updateSuccessful = db.update("records", values, where, whereArgs) > 0;
        db.close();

        return updateSuccessful;

    }

    //delete record
    public boolean delete(String id) {
        boolean deleteSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        deleteSuccessful = db.delete("records", "id ='" + id + "'", null) > 0;
        db.close();
        return deleteSuccessful;
    }

}