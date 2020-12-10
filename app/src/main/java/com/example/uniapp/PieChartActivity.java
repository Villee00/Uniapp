package com.example.uniapp;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        PieChart pieChart = findViewById(R.id.pieChart);
        int q = 0;
        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> on = db.uniDao().loadAllUni();

        //PiirakkaEntry
        ArrayList<PieEntry> sleep = new ArrayList<>();
        for(int i=0; i< on.size(); i++){
            on.get(i).getDuration();
            sleep.add(new PieEntry(i, on.get(i).getDuration())); }

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