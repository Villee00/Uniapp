package com.example.uniapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        //nukkumisen viikkotilasto
        //BarEntry x ja y pitää muuttaa päivämääriksi ja syötetyiksi unen määriksi
        //nykyset numerot testimielessä

        BarChart barChart = findViewById(R.id.barChart);

        //unen määrä esimerkki
        ArrayList<BarEntry> hoursOfSleep = new ArrayList<>();
        hoursOfSleep.add(new BarEntry(1, 8));
        hoursOfSleep.add(new BarEntry(2, 7));
        hoursOfSleep.add(new BarEntry(3, 5));
        hoursOfSleep.add(new BarEntry(4, 8));
        hoursOfSleep.add(new BarEntry(5, 6));
        hoursOfSleep.add(new BarEntry(6, 6));
        hoursOfSleep.add(new BarEntry(7, 6));


        //palkkien ulkonäkö
        BarDataSet barDataSet = new BarDataSet(hoursOfSleep, "Hours of Sleep");
        barDataSet.setColors(Color.BLUE);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);


        //luo tilasto
        BarData barData = new BarData(barDataSet);

        //koko tilaston ulkonäkö
        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);
        //barChart.groupBars(0, (float) 0.1, 0);
        barChart.setDragEnabled(true);


        //nappi vie unen (kuukausi?) yhteenvetoon: PieChartActivity
        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });
    }
}