package com.example.uniapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.GregorianCalendar;
/**
 * This class includes the variables and methods associated with saving sleeping data as an object
 * @author Jussi Enne
 * @version 1.0 12/2020
 */

@Entity(tableName = "uni_table")
public class Uni {
    @PrimaryKey(autoGenerate = true)

    public int uid;
    private float duration;
    private Calendar pvm;
    private int quality;
    private String note;

    /**
     * Constructor to create a Uni object.
     * @param duration sleep duration in hours
     * @param pvm date when the sleeping began as a Calendar object
     * @param quality quality of the sleep in percent
     * @param note a note associated with each sleeping entry
     */
    public Uni(float duration, Calendar pvm, int quality, String note){
        this.duration = duration;
        this.pvm = pvm;
        this.quality = quality;
        this.note = note;
    }

    /**
     * Method to get the duration of the sleep
     * @return float duration duration of the sleep in hours
     */
    public float getDuration(){
        return duration;
    }
    /**
     * Method to get the start date of the sleep
     * @return Calendar pvm start date of the sleeping as a Calendar object
     * */
    public Calendar getPvm(){
        return this.pvm;
    }
    /**
     * Method to get the quality of the sleep
     * @return int quality quality of the sleep in percent
     * */
    public int getQuality(){
        return quality;
    }
    /**
     * Method to get the note associated with the sleep entry
     * @return String note note associated with the sleep entry
     * */
    public String getNote(){
        return note;
    }

    public void setDuration(float duration) { this.duration = duration; }
    public void setPvm(Calendar pvm) { this.pvm = pvm; }
    public void setQuality(int quality) { this.quality = quality; }
    public void setNote(String note) { this.note = note; }


    @Override
    public String toString() {
        return "Date: " + this.pvm.getTime();
    }
}
