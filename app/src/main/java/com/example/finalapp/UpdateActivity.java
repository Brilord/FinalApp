package com.example.finalapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    EditText title, desc;
    Button update, btnTime, btnDate;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title = findViewById(R.id.title);
        desc = findViewById(R.id.description);
        btnTime = findViewById(R.id.timebtn);
        btnDate = findViewById(R.id.datebtn);


        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        desc.setText(i.getStringExtra("desc"));
        id=i.getStringExtra("id");
        btnTime.setText(i.getStringExtra("time"));
        btnDate.setText(i.getStringExtra("date"));

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(desc.getText().toString())
        && !TextUtils.isEmpty(btnTime.getText().toString()) && !TextUtils.isEmpty(btnDate.getText().toString())) {
            Database database = new Database(UpdateActivity.this);
            database.updateNotes(title.getText().toString(),desc.getText().toString(),id,
                    btnTime.getText().toString(), btnDate.getText().toString());
            startActivity(new Intent(UpdateActivity.this,MainActivity.class));
        } else {
            Toast.makeText(UpdateActivity.this, "", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        btnDate.setText(currentDateString);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        btnTime.setText("Hour: " + hourOfDay + " Minute: " + minute);

    }
}