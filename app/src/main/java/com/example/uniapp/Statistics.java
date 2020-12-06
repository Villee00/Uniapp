package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Date;

public class Statistics extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
    }

    public ArrayList<String> getNotes(Date start, Date end){
        ArrayList<String> notes = new ArrayList<String>();

        //Something here to get the right notes

        return notes;
    }
}