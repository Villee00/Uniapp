package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
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

    public ArrayList<String> getNotes(){
        ArrayList<String> notes = new ArrayList<>();
        ArrayList<Uni> unet = (ArrayList<Uni>) uniDatabase.getInstance(this).uniDao().loadAllUni();

        for (int i = 0; i < unet.size(); i++){
            notes.add(unet.get(i).getNote());
        }
        return notes;
    }
}