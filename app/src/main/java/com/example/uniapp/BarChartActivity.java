package com.example.uniapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class BarChartActivity extends AppCompatActivity {
    ArrayList<String> labelNames = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart barChart = findViewById(R.id.barChart);

        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> on = db.uniDao().loadAllUni();

        ArrayList<BarEntry> hoursOfSleep = new ArrayList<>();


        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM"); //Select the format in which the date will be displayed

        for (int i = 0; i < on.size(); i++) {
            hoursOfSleep.add(new BarEntry(i, on.get(i).getDuration()));
            String date = sdf.format(on.get(i).getPvm().getTime());
            labelNames.add(date);
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
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setLabelRotationAngle(120);
        xAxis.setDrawLabels(true);
        //xAxis.setAxisMaximum(7);
        xAxis.setLabelCount(labelNames.size());

        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labelNames.get((int)value);
            }
        });

        barChart.invalidate();
        //nappi vie unien yhteenvetoon: PieChartActivity
        findViewById(R.id.buttonPieChart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PieChartActivity.class));
            }
        });

        findViewById(R.id.buttonNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notes.class ));
            }
        });
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