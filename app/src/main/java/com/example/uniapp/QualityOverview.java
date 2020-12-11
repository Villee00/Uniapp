package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;
/**
 * Class for showing a counter for sleep qualities
 * @author Karina Raikula
 * @version 1.0 12/2020
 */
public class QualityOverview extends AppCompatActivity {

    public TextView bestSleepInput;
    public TextView okSleepInput;
    public TextView badSleepInput;

/**
* Creating and initialising counters for three different sleep qualities
*/
    int bestSleepCounter = 0;
    int okSleepCounter = 0;
    int badSleepCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_overview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> on = db.uniDao().loadAllUni();
/**
 * Connecting TextWiew variables to their layout file counterparts
 */
        bestSleepInput = findViewById(R.id.textViewBestInput);
        okSleepInput = findViewById(R.id.textViewOkInput);
        badSleepInput = findViewById(R.id.textViewBadInput);

/**
 * same for loop as in BarChartActivity to count different types of quality inputs
 * inputs of quality added to their respective counters and set as text to show the user
 */
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