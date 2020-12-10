package com.example.uniapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class is a dataobject for Database calling
 * @author Ville Haapamäki
 * @version 1.0 12/2020
 */
@Dao
public interface UniDao {
    /**
     * This is used when you want to insert data to database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUni(Uni... uni);

    /**
     * Delete a specifict uni in the database.
     */
    @Query("DELETE FROM uni_table WHERE uid = :id")
    void deleteUni(int id);

    /**
     * Query to get everything from the table to an list
     * Order by newest date to old date.
     */
    @Query("SELECT * FROM uni_table ORDER BY pvm ASC")
    List<Uni> loadAllUni();

    /**
     * Query to get objects from specifict time frame
     */
    @Query("SELECT * FROM uni_table WHERE pvm BETWEEN :from AND :to")
    List<Uni> loadUniDates(Calendar from, Calendar to);

    /**
     * Delete a specifict uni in the database.
     */
    @Query("DELETE FROM uni_table")
    void deleteUni();
}
