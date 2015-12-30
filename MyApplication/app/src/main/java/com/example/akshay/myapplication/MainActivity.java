package com.example.akshay.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateLocation = (Button) findViewById(R.id.buttonCreateRecord);
        buttonCreateLocation.setOnClickListener(new OnClickListenerCreateRecord(this));

        countRecords();
        readRecords();

    }

    //count record
    public void countRecords(){
        int recordCount = new TableControllerRecords(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");

    }

    //read records
    public void readRecords() {

        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        List<ObjectRecord> records = new TableControllerRecords(this).read();

        if (records.size() > 0) {

            for (ObjectRecord obj : records) {

                int id = obj.id;
                String price = obj.price;
                String note = obj.note;
                String category = obj.categoryOfRecord;
                String date = obj.dateOfRecord;

                String textViewContents = price + " - " + note + " - " + category + " - " + date;

                TextView textViewRecordItem= new TextView(this);
                textViewRecordItem.setPadding(0, 10, 0, 10);
                textViewRecordItem.setText(textViewContents);
                textViewRecordItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewRecordItem);
                //click listener for edit item
                textViewRecordItem.setOnLongClickListener(new OnLongClickListenerRecord(this));
            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

    }


}
