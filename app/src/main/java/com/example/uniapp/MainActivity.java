package com.example.uniapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This is the main activity, and it mostly controls the starting screen
 * of the app and the functionality within, such as calculating the amount slept
 * and storing it, the date, the quality and notes into the database
 * @author Joona Puustelli
 */
public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
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

    /**
     * the on create function doesen't control much other than the on screen
     * clock and wether or not the user wants to access the history tab
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateTimeDisplay = (TextClock)findViewById(R.id.textViewDate);

        calendar = Calendar.getInstance();

        db = uniDatabase.getInstance(this);
        //button for accessing the history tab
        findViewById(R.id.buttonBarChart).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), BarChartActivity.class));
            }
        });
    }

    /**
     * this function is for when the user presses the "go to sleep" button, it saves the
     * time when the user went to sleep so that we can later calculate the amount slept
     * @param v
     */
    public void nukkumaan(View v){

        // initializing buttons
        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);

        // setting necessary buttons visible
        nukkumaan.setVisibility(View.INVISIBLE);
        herätys.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.VISIBLE);
        nukkumaanmenoAika.setVisibility(View.VISIBLE);

        // clearing cache
        tallennettavaMuistiinpano = "";

        // saving the time the user wnet to sleep
        calendar = Calendar.getInstance();
        laskettavaTunnit = new SimpleDateFormat("HH");
        laskettavaMinuutit = new SimpleDateFormat("mm");
        nukkumaanMenoTunnit = laskettavaTunnit.format(calendar.getTime());
        nukkumaanmenoMinuutit = laskettavaMinuutit.format(calendar.getTime());

        //shows the user the time they went to sleep
        dateFormat = new SimpleDateFormat("HH:mm");
        date = dateFormat.format(calendar.getTime());
        nukkumaanmenoAika.setText("Menty nukkumaan " + date);
    }

    /**
     * This function provides a simple button to cancel in case the user
     * accidentally presses the go to sleep button
     * @param v
     */
    public void peruutus(View v){

        // initializing buttons
        Button herätys = (Button)findViewById(R.id.herätysButton);
        Button nukkumaan = (Button)findViewById(R.id.nukkumaanButton);
        Button peruutus = (Button)findViewById(R.id.peruutusButton);
        nukkumaanmenoAika = (TextView)findViewById(R.id.nukkumaanmenoAika);

        //setting things visible/invisible
        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.INVISIBLE);
        nukkumaanmenoAika.setVisibility(View.INVISIBLE);

        // clearing cache
        nukkumaanMenoTunnit = "";
        nukkumaanmenoMinuutit = "";
    }

    /**
     * This is the most complicating function for when the user presses the wake up button.
     * It calculates the time slept and then prompts the user to evaluate the quality of their sleep
     * and add any notes if they so please
     * @param v
     */
    public void herätys(View v){

        // initialize buttons
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
        ImageView imageView = (ImageView)findViewById(R.id.imageViewbackground);

        // setting necessary things visible/invisible
        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        peruutus.setVisibility(View.INVISIBLE);
        nukkumaanmenoAika.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        // saving the time the user woke up
        calendar = Calendar.getInstance();
        laskettavaTunnit = new SimpleDateFormat("HH");
        laskettavaMinuutit = new SimpleDateFormat("mm");
        herätysTunnit = laskettavaTunnit.format(calendar.getTime());
        herätysMinuutit = laskettavaMinuutit.format(calendar.getTime());

        // turning strings into integers for easy calculating
        ntunnit = Integer.parseInt(nukkumaanMenoTunnit);
        nminuutit = Integer.parseInt(nukkumaanmenoMinuutit);
        htunnit = Integer.parseInt(herätysTunnit);
        hminuutit = Integer.parseInt(herätysMinuutit);

        // calculating the time slept
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

        //turning more things visible/invisible
        nukuttuAika.setText(nukuttuTunnit + " h " + nukuttuMinuutit + " min");
        nukuttuAika.setVisibility(View.VISIBLE);
        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        laatu.setVisibility(View.VISIBLE);
        tallenna.setVisibility(View.VISIBLE);
        arvioi.setVisibility(View.VISIBLE);
        muisti.setVisibility(View.VISIBLE);
        muistiinpano.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.VISIBLE);

        nukuttuAika.bringToFront();
        nukkumaan.bringToFront();
        laatu.bringToFront();
        tallenna.bringToFront();
        arvioi.bringToFront();
        muisti.bringToFront();
        muistiinpano.bringToFront();

    }

    /**
     * This last function is for saving the data into the database. It functions when the user
     * presses the save button after being prompted to evakuate their sleep
     * @param v
     */
    public void tallennus(View v){

        // initializing buttons
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
        ImageView imageView = (ImageView)findViewById(R.id.imageViewbackground);

        // turning necessary things visible/invisible
        herätys.setVisibility(View.INVISIBLE);
        nukkumaan.setVisibility(View.VISIBLE);
        nukuttuAika.setVisibility(View.INVISIBLE);
        laatu.setVisibility(View.INVISIBLE);
        tallenna.setVisibility(View.INVISIBLE);
        arvioi.setVisibility(View.INVISIBLE);
        muisti.setVisibility(View.INVISIBLE);
        muistiinpano.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.INVISIBLE);

        // saving data into the database
        tallennettavaMuistiinpano = muistiinpano.getText().toString();
        unenLaatu = laatu.getProgress();
        calendar.getTime();
        int duration = (nukuttuTunnit * 60) + nukuttuMinuutit;

        db.uniDao().addUni(new Uni(duration, calendar, unenLaatu, tallennettavaMuistiinpano));
    }
}