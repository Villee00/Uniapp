package com.example.uniapp.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.Date;
import java.util.List;

@Dao
public interface UniDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void addUni(Uni uni);

    @Delete
    public void deleteUni(Uni uni);

    @Query("SELECT * FROM uni_table")
    public List<Uni> loadAllUni();

    @Query("SELECT * FROM uni_table /*WHERE paivamaara BETWEEN :from AND :to*/")
    public List<Uni> loadUniDates(/*Date from, Date to*/);
}
