package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;

public class Notes extends AppCompatActivity {
    Calendar start;
    Calendar end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        ListView lv = findViewById(R.id.lv_notes);
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                Statistics.getNotes()
        ));
    }
}