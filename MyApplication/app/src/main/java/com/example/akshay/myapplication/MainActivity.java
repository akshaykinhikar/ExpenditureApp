package com.example.akshay.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCreateLocation = (Button) findViewById(R.id.buttonCreateRecord);
        buttonCreateLocation.setOnClickListener(new OnClickListenerCreateRecord());

    }
}
