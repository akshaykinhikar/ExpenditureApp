package com.example.akshay.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateLocation = (Button) findViewById(R.id.buttonCreateRecord);
        buttonCreateLocation.setOnClickListener(new OnClickListenerCreateRecord());

        countRecords();



    }

    //count record
    public void countRecords(){
        int recordCount = new TableControllerStudent(this).count();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");

    }


}
