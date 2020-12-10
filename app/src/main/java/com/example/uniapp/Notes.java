package com.example.uniapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * This activity is for showing the notes in a ListView
 * @author Jussi Enne
 * @version 1.0 12/2020
 */
public class Notes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        updateUI();
        ArrayList<String> notes = new ArrayList<>();
        uniDatabase db = uniDatabase.getInstance(this);
        final ArrayList<Uni> unet = (ArrayList<Uni>) db.uniDao().loadAllUni();

    }

    /**
     * Used to update the listview if needed
     */
    private void updateUI(){
        ListView lv = findViewById(R.id.lv_notes);
        uniDatabase db = uniDatabase.getInstance(this);
        List<Uni> unet = db.uniDao().loadAllUni();

        CustomAdapter adapter = new CustomAdapter(this, R.layout.note_row, (ArrayList<Uni>) unet);
        lv.invalidateViews();
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Adding an clicker object to the listview components, so we can use that to delete them.
             */
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Uni selectedItem = (Uni) parent.getItemAtPosition(position);

                Log.d("UID", Integer.toString(unet.get(position).getUid()));
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    /**
                     * When an listview object is clicked this will open a ask promt where user has
                     * to confirm if he wants to delete clicked object from database
                     * @param dialog dialogs interface
                     * @param which get the pressed button index
                     */
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
                builder.setMessage("Haluatko poistaa unen muistista?").setPositiveButton("Kyllä", dialogClickListener)
                        .setNegativeButton("En", dialogClickListener).show();
            }
        });
    }


}