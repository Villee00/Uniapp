package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Calendar;
/**
 * This activity is for showing the notes in a ListView
 * @author Jussi Enne
 * @version 1.0 12/2020
 */
public class Notes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Statistics stats = new Statistics();
        setContentView(R.layout.activity_notes);
        ListView lv = findViewById(R.id.lv_notes);
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stats.getNotes()                        //ArrayList of strings to be shown in the ListView
        ));
    }
}