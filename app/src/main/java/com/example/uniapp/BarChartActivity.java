package com.example.uniapp;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for creating and showing sleep data in a bar chart
 * @author Karina Raikula
 * @version 1.0 12/2020
 */
public class BarChartActivity extends AppCompatActivity {
    ArrayList<String> labelNames = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        BarChart barChart = findViewById(R.id.barChart);

        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> on = db.uniDao().loadAllUni();
/**
 * An ArrayList hoursOfSleep is created to store user inputs of sleep time
 * User inputs are added to the list using a for-loop
 * Dates of inputs are saved to another ArrayList labelNames using the same loop
 * SimpleDateFormat class to choose the format in which the date will be displayed
 */
        ArrayList<BarEntry> hoursOfSleep = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM");

        for (int i = 0; i < on.size(); i++) {
            hoursOfSleep.add(new BarEntry(i, on.get(i).getDuration()));
            String date = sdf.format(on.get(i).getPvm().getTime());
            labelNames.add(date);
        }

/**
 * MPChart is used to generate the chart in this activity
 * Creating the bar chart from ArrayList hoursOfSleep
  */

        BarDataSet barDataSet = new BarDataSet(hoursOfSleep, "Unihistoria");
        BarData barData = new BarData(barDataSet);
/**
 * Setting chart functions and visual details
 */
        barDataSet.setColor(Color.GRAY);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        barChart.setFitBars(true);
        barChart.getDescription().setText("Unihistoriasi");
        barChart.animateY(2000);
        barChart.setDragEnabled(true);
        barChart.setData(barData);
        barChart.setDrawValueAboveBar(true);
        barChart.setHorizontalScrollBarEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(7);

 /**
  * Accessing the X Axis of the chart to add visual details
  */
        XAxis xAxis = barChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setLabelCount(labelNames.size());
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawGridLines(false);
            xAxis.setAvoidFirstLastClipping(true);
            xAxis.setLabelRotationAngle(45);
            xAxis.setDrawLabels(true);
/**
  * Method to get the dates saved to the list labelNames to be put as the labels of X axis
  * @return String int value returns the date of a sleep entry as a bar label on the X axis
  *
  */
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return labelNames.get((int)value);
            }
        });

        barChart.invalidate();
/**
 * Button that takes the user to view their sleep quality counter
 */
        findViewById(R.id.buttonQualityOverview).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), QualityOverview.class));
            }
        });
/**
 * Button that takes the user to view their notes
 */
        findViewById(R.id.buttonNotes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Notes.class ));
            }
        });
    }
}