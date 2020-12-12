package com.example.uniapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Customadapter class to show Uni notes nicer
 * @author Ville Haapamäki
 */
class CustomAdapter extends ArrayAdapter<Uni> {
    Context context;
    int mResource;

    /**
     * constructor for the class
     * @param context context that is being used when this is called
     * @param resource that is wanted to be used
     * @param unet List of all the Unet that are wanted to get included in note show
     */
    public CustomAdapter(Context context,int resource, ArrayList<Uni> unet) {
        super(context, resource, unet);
        this.mResource = resource;
        this.context = context;
    }

    /**
     * Method to get noteview and put the correct information in correct position
     * @param position the object thats information is being inserted to the view
     * @param convertView
     * @param parent viewgroup that has all the fields so note_row.xml
     * @return note view that has all the values inserted into it
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //format the date to correct format
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH:mm");
        String date = sdf.format(getItem(position).getPvm().getTime());
        String note = getItem(position).getNote();
        int minutes = (int) getItem(position).getDuration() % 60;
        int hours = (int) getItem(position).getDuration() / 60;
        String amount =  hours + " tuntia " + minutes + " minuuttia";
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(mResource, parent, false);

        TextView dateText = (TextView) convertView.findViewById(R.id.DateText);
        TextView noteText = (TextView) convertView.findViewById(R.id.NoteText);
        TextView amountText = (TextView) convertView.findViewById(R.id.SleptAmount);

        dateText.setText(date);
        noteText.setText(note);
        amountText.setText(amount);

        return convertView;
    }
}