package com.example.uniapp;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        PieChart pieChart = findViewById(R.id.pieChart);

        //PieEntry value ja data pitää muuttaa halutuksi ajoiksi
        ArrayList<PieEntry> sleep = new ArrayList<>();
        sleep.add(new PieEntry(1, 8));
        sleep.add(new PieEntry(2, 6.5));
        sleep.add(new PieEntry(3, 7.5));
        sleep.add(new PieEntry(4, 6));
        sleep.add(new PieEntry(5, 3.4));
        sleep.add(new PieEntry(6, 7));
        sleep.add(new PieEntry(7, 8));


        //piirakan ulkonäkö
        PieDataSet pieDataSet = new PieDataSet(sleep, "Sleep");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.setCenterText("Sleep");
        pieChart.animate();
    }
}