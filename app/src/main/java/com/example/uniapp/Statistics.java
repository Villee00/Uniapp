package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public ArrayList<String> getNotes(Calendar start, Calendar end){
        ArrayList<String> notes = new ArrayList<String>();

        //Something here to get the right notes

        return notes;
    }
}