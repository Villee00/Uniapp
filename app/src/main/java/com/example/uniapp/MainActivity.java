package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView dateTimeDisplay;
    private TextView nukkumaanmenoAika;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat laskettavaTunnit;
    private SimpleDateFormat laskettavaMinuutit;
    private String date;
    private String nukkumaanMenoTunnit;
    private String nukkumaanmenoMinuutit;
    private String herätysTunnit;
    private String herätysMinuutit;
    int ntunnit = 0;
    int nminuutit = 0;
    int htunnit = 0;
    int hminuutit = 0;
    int nukuttuMinuutit = 0;
    int nukuttuTunnit = 0;
    private TextView nukuttuAika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTimeDisplay = (TextView)findViewById(R.id.textViewDate);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("HH:mm");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

    }

    public void nukkumaan(View v){
        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);

        nukkumaan.setVisibility(View.INVISIBLE);
        herätys.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.VISIBLE);
        nukkumaanmenoAika.setVisibility(View.VISIBLE);

        calendar = Calendar.getInstance();
        laskettavaTunnit = new SimpleDateFormat("HH");
        laskettavaMinuutit = new SimpleDateFormat("mm");
        nukkumaanMenoTunnit = laskettavaTunnit.format(calendar.getTime());
        nukkumaanmenoMinuutit = laskettavaMinuutit.format(calendar.getTime());

        nukkumaanmenoAika.setText("Menty nukkumaan " + date);
    }
    public void peruutus(View v){
        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);

        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.INVISIBLE);
        nukkumaanmenoAika.setVisibility(View.INVISIBLE);

        nukkumaanMenoTunnit = "";
        nukkumaanmenoMinuutit = "";
    }
    public void herätys(View v){
        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);
        nukuttuAika = (TextView)findViewById(R.id.nukuttuAika);

        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.INVISIBLE);
        nukkumaanmenoAika.setVisibility(View.INVISIBLE);

        calendar = Calendar.getInstance();
        laskettavaTunnit = new SimpleDateFormat("HH");
        laskettavaMinuutit = new SimpleDateFormat("mm");
        herätysTunnit = laskettavaTunnit.format(calendar.getTime());
        herätysMinuutit = laskettavaMinuutit.format(calendar.getTime());

        ntunnit = Integer.parseInt(nukkumaanMenoTunnit);
        nminuutit = Integer.parseInt(nukkumaanmenoMinuutit);
        htunnit = Integer.parseInt(herätysTunnit);
        hminuutit = Integer.parseInt(herätysMinuutit);

        if(ntunnit > htunnit)
            nukuttuTunnit = (24 - ntunnit) + htunnit;
        else if (htunnit > ntunnit)
            nukuttuTunnit = htunnit - ntunnit;
        else
            nukuttuTunnit = 00;

        if(nminuutit < hminuutit)
            nukuttuMinuutit = hminuutit - nminuutit;
        else if (nminuutit > hminuutit){
            nukuttuMinuutit = (60 - nminuutit) + hminuutit;
            nukuttuTunnit--;
        }else
            nukuttuMinuutit = 00;

        nukuttuAika.setText(nukuttuTunnit + " h " + nukuttuMinuutit + " min");
        nukuttuAika.setVisibility(View.VISIBLE);
        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
    }
}