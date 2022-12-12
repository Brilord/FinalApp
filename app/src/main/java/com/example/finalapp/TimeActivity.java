package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TimePicker;

public class TimeActivity extends AppCompatActivity {
    TimePicker timePicker;
    Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);
        timePicker =findViewById(R.id.timePicker);
    }
}