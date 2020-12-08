package com.example.uniapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Dao
public interface UniDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUni(Uni... uni);

    @Query("DELETE FROM uni_table WHERE uid = :id")
    void deleteUni(int id);

    //Valitsee kaikki unet tietokannasta
    @Query("SELECT * FROM uni_table")
    List<Uni> loadAllUni();

    /*Valitsee unet aika väliltä joka annetaan.
    * Esim. Viikko sitte merkitään calendar.add(Calendar.DATE, -7);
    * */
    @Query("SELECT * FROM uni_table WHERE pvm BETWEEN :from AND :to")
    List<Uni> loadUniDates(Calendar from, Calendar to);
}
