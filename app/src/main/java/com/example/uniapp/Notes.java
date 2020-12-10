package com.example.uniapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
/**
 * This activity is for showing the notes in a ListView
 * @author Jussi Enne
 * @version 1.0 12/2020
 */
public class Notes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Statistics stats = new Statistics();
        setContentView(R.layout.activity_notes);
        ListView lv = findViewById(R.id.lv_notes);
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stats.getNotes()                        //ArrayList of strings to be shown in the ListView
        ));
        ArrayList<String> notes = new ArrayList<>();
        uniDatabase db = uniDatabase.getInstance(this);
        final ArrayList<Uni> unet = (ArrayList<Uni>) db.uniDao().loadAllUni();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);

                Log.d("UID", Integer.toString(unet.get(position).getUid()));
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                db.uniDao().deleteUni(unet.get(position).getUid());
                                updateUI();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Haluat poistaa unen muisitsta?").setPositiveButton("Kyllä", dialogClickListener)
                        .setNegativeButton("En", dialogClickListener).show();
            }
        });
    }

    private void updateUI(){
        Statistics stats = new Statistics();
        ListView lv = findViewById(R.id.lv_notes);
        lv.setAdapter(new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                stats.getNotes()                        //ArrayList of strings to be shown in the ListView
        ));
    }
}