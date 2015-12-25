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
        buttonCreateLocation.setOnClickListener(new OnClickListenerCreateRecord());

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
                String firstname = obj.firstname;
                String email = obj.email;

                String textViewContents = firstname + " - " + email;

                TextView textViewStudentItem= new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
                //click listener for edit item
                textViewStudentItem.setOnLongClickListener(new OnLongClickListenerRecord());
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
