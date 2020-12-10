package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class QualityOverview extends AppCompatActivity {

    public TextView bestSleepInput;
    public TextView okSleepInput;
    public  TextView badSleepInput;

    int bestSleepCounter = 0;
    int okSleepCounter = 0;
    int badSleepCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_overview);

        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> on = db.uniDao().loadAllUni();

        bestSleepInput = findViewById(R.id.textViewBestInput);
        okSleepInput = findViewById(R.id.textViewKeskivertoInput);
        badSleepInput = findViewById(R.id.textViewBadInput);

        for (int i = 0; i < on.size(); i++) {
            if (on.get(i).getQuality() < 40) {
                badSleepCounter++;
                badSleepInput.setText(badSleepCounter+"");
            }
            if (on.get(i).getQuality() >= 40 && on.get(i).getQuality() < 60) {
                okSleepCounter++;
                okSleepInput.setText(okSleepCounter+"");
            }
            if (on.get(i).getQuality() >= 60 && on.get(i).getQuality() <= 100) {
                bestSleepCounter++;
                bestSleepInput.setText(bestSleepCounter+"");
            }
        }
    }
}