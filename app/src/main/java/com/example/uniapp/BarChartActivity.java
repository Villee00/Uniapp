package com.example.uniapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart barChart = findViewById(R.id.barChart);

        uniDatabase db = Room.databaseBuilder(getApplicationContext(), uniDatabase.class, "unet")
                .allowMainThreadQueries()
                .build();

        db.uniDao().loadAllUni();
        List<Uni> on = db.uniDao().loadAllUni();

        ArrayList<BarEntry> hoursOfSleep = new ArrayList<>();
        ArrayList<Integer> labelNames = new ArrayList<>();

        int q = Calendar.DATE;

        for (int i = 1; i < on.size(); i++) {
            on.get(i).getDuration();
            hoursOfSleep.add(new BarEntry(i, on.get(i).getDuration()));
            labelNames.add(q);
        }

        BarDataSet barDataSet = new BarDataSet(hoursOfSleep, "Hours of Sleep");
        BarData barData = new BarData(barDataSet);

        barDataSet.setColor(Color.GRAY);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.setFitBars(true);

        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);
        barChart.setDragEnabled(true);
        barChart.setData(barData);
        barChart.setDrawValueAboveBar(true);
        barChart.setHorizontalScrollBarEnabled(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.TOP);
        xAxis.setValueFormatter(new MyAxisValues());
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(120);
        xAxis.setDrawLabels(true);
        //xAxis.setAxisMaximum(7);
        //xAxis.setLabelCount(labelNames.size());


        //nappi vie unien yhteenvetoon: PieChartActivity
        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });

 /*       findViewById(R.id.buttonNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notes.class ));
            }
        });


  */
    }
    public class MyAxisValues extends ValueFormatter{

        public MyAxisValues(){

        }
    }


}



/*
        if (on.get(q).getQuality() < 25) {
            barDataSet.setColor(Color.GRAY);
        }
        else if (on.get(q).getQuality() >= 25 && on.get(q).getQuality() < 50) {
            barDataSet.setColor(Color.YELLOW);
        }
        else if (on.get(q).getQuality() >= 50 && on.get(q).getQuality() <= 75) {
            barDataSet.setColor(Color.CYAN);
        }
        else if (on.get(q).getQuality() > 75 && on.get(q).getQuality() <= 100) {
            barDataSet.setColor(Color.GREEN);
        }

 */