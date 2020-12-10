package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
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
    private String tallennettavaMuistiinpano;
    int ntunnit = 0;
    int nminuutit = 0;
    int htunnit = 0;
    int hminuutit = 0;
    int nukuttuMinuutit = 0;
    int nukuttuTunnit = 0;
    int unenLaatu = 0;
    private TextView nukuttuAika;

    public uniDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTimeDisplay = (TextView)findViewById(R.id.textViewDate);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("HH:mm");
        date = dateFormat.format(calendar.getTime());
        dateTimeDisplay.setText(date);

        db = uniDatabase.getInstance(this);
        //nappi nukkumisen historiaan
        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
            }
        });
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

        tallennettavaMuistiinpano = "";

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
        Button tallenna = (Button)findViewById(R.id.tallennaButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);
        nukuttuAika = (TextView)findViewById(R.id.nukuttuAika);
        SeekBar laatu = (SeekBar)findViewById(R.id.laatu);
        TextView arvioi = (TextView)findViewById(R.id.arvioi);
        TextView muisti = (TextView)findViewById(R.id.muisti);
        TextView muistiinpano = (TextView)findViewById(R.id.muistiinpano);

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
        laatu.setVisibility(View.VISIBLE);
        tallenna.setVisibility(View.VISIBLE);
        arvioi.setVisibility(View.VISIBLE);
        muisti.setVisibility(View.VISIBLE);
        muistiinpano.setVisibility(View.VISIBLE);
    }
    public void tallennus(View v){

        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        Button tallenna = (Button)findViewById(R.id.tallennaButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);
        nukuttuAika = (TextView)findViewById(R.id.nukuttuAika);
        SeekBar laatu = (SeekBar)findViewById(R.id.laatu);
        TextView arvioi = (TextView)findViewById(R.id.arvioi);
        TextView muisti = (TextView)findViewById(R.id.muisti);
        TextView muistiinpano = (TextView)findViewById(R.id.muistiinpano);

        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        nukuttuAika.setVisibility(View.INVISIBLE);
        laatu.setVisibility(View.INVISIBLE);
        tallenna.setVisibility(View.INVISIBLE);
        arvioi.setVisibility(View.INVISIBLE);
        muisti.setVisibility(View.INVISIBLE);
        muistiinpano.setVisibility(View.INVISIBLE);

        tallennettavaMuistiinpano = muistiinpano.getText().toString();
        unenLaatu = laatu.getProgress();
        calendar.getTime();
        int duration = (nukuttuTunnit * 60) + nukuttuMinuutit;

        db.uniDao().addUni(new Uni(duration, calendar, unenLaatu, tallennettavaMuistiinpano));
    }
}