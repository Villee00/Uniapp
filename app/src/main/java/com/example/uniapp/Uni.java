package com.example.uniapp;

import java.util.Date;

public class Uni {
    private float duration;
    private Date pvm;
    private int quality;
    private String note;

    public Uni(float duartion, Date pvm, int quality, String note){
        this.duration = duration;
        this.pvm = pvm;
        this.quality = quality;
        this.note = note;
    }
    public Uni(float duration, Date pvm, int quality){
        this.duration = duration;
        this.pvm = pvm;
        this.quality = quality;
    }

    public float getDuration(){
        return duration;
    }
    public Date getPvm(){
        return this.pvm;
    }
    public int getQuality(){
        return quality;
    }
    public String getNote(){
        return note;
    }
}
