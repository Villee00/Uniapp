package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Statistics extends AppCompatActivity {
    //public static final String EXTRA = "com.example.uniapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public void viewNotes() {
        Intent nextActivity = new Intent(Statistics.this, Notes.class);
        startActivity(nextActivity);
    }

    public static ArrayList<String> getNotes(){
        ArrayList<String> notes = new ArrayList<>();

        //Something here to get the right notes
        /*Uni uni = new Uni(8, Calendar.getInstance(), "Hyvät unet");
        notes.add(uni.getNote());*/

        return notes;
    }
}