package com.example.finalapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{
    EditText title, description;
    Button addNoteButton, dateButton, timeButton;
    TimePicker timePicker;
    DatePicker datePicker;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        title = findViewById(R.id.title_edit);
        description = findViewById(R.id.description_edit);
        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
//        timePicker = findViewById(R.id.timePicker);
//        timePicker.setIs24HourView(true);
//        datePicker = findViewById(R.id.datePicker);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d("NEW", "HELLO");
        if (!TextUtils.isEmpty(title.getText().toString()) || !TextUtils.isEmpty(description.getText().toString())) {
            Database db = new Database(AddNotesActivity.this);
            db.addNotes(title.getText().toString(), description.getText().toString());

            Intent intent = new Intent(AddNotesActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();

        } else {
            Toast.makeText(AddNotesActivity.this, "Both Fields Required.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.eventActivity1:
                startActivity(new Intent(getApplicationContext(), EventsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        
    }
}