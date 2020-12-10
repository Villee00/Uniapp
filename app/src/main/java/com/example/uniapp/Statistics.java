package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Statistics extends AppCompatActivity {

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
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM"); //Select the format in which the date will be displayed
            notes.add(sdf.format(unet.get(i).getPvm().getTime()) + " " + unet.get(i).getDuration() + "h " + unet.get(i).getNote()); //construct the notes string
        }
        return notes;
    }
}