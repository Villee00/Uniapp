package com.example.uniapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import java.util.Calendar;
import java.util.List;

/**
 * This interface is meant to have all the methods that can be used to communicate
 * with the local database
 * @author Ville Haapamäki
 */
@Dao
public interface UniDao {
    /**
     * Inserting Uni objects to database
     * @param uni can be given as an one object or list of Uni:s also
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUni(Uni... uni);

    /**
     * Delete an object from the database
     * @param id delete the object that matches given id
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
     * LoaduniDates gets all the dates between them
     * @param from Date that date search is starting
     * @param to Date that is the last day to be included in the search
     * @return List of Uni that has all the data between given dates
     */
    @Query("SELECT * FROM uni_table WHERE pvm BETWEEN :from AND :to")
    List<Uni> loadUniDates(Calendar from, Calendar to);

    /**
     * Delete a specifict uni in the database.
     */
    @Query("DELETE FROM uni_table")
    void deleteAllUni();
}
