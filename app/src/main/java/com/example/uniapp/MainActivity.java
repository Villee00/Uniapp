package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    uniDatabase db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tekeee databasei tällä
        db = Room.databaseBuilder(getApplicationContext(), uniDatabase.class, "unet")
                .allowMainThreadQueries()
                .build();

        //Näi voi lisää teitoja
        //db.uniDao().addUni(new Uni(12, Calendar.getInstance(),"Oli hyvat unet"));
    }
}