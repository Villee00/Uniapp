package com.example.uniapp.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "uni_table")
public class Uni {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    public int kesto;
    //public Date paivamaara;
    public String muistiinpano;

    public Uni(int kesto, String muistiinpano) {
        this.kesto = kesto;
      //  this.paivamaara = paivamaara;
        this.muistiinpano = muistiinpano;
    }
    public Uni(){
        this.kesto = 0;
        //this.paivamaara = null;
        this.muistiinpano = "muistiinpano";
    }
}
