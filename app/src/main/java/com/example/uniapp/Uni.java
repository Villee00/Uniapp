package com.example.uniapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Entity(tableName = "uni_table")
public class Uni {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    private float duration;
    private Calendar pvm;
    private int quality;
    private String note;



    public Uni(float duration, Calendar pvm, int quality, String note){
        this.duration = duration;
        this.pvm = pvm;
        this.quality = quality;
        this.note = note;
    }

    public float getDuration(){
        return duration;
    }
    public Calendar getPvm(){
        return this.pvm;
    }
    public int getQuality(){
        return quality;
    }
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
