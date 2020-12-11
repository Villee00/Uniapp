package com.example.uniapp;

import androidx.room.TypeConverter;

import java.util.Calendar;

/**
 * Class to convert Calendar value to the long so we can store it to the local database
 * @author Ville Haapamäki
 */
public class Converters {
    /**
     * Change an long obejct back to Calendar object
     * @param l long that is wanted to be switch to Calendar object
     * @return Calendar object with the value of given long
     */
    @TypeConverter
    public static Calendar toCalendar(Long l) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(l);
        return c;
    }

    /**
     * Change Calendar obeject to long object so it can be stored in the local database
     * @param c Calendar value that is wanted to change to long
     * @return  Long obejct so it can be store to the database
     */
    @TypeConverter
    public static Long fromCalendar(Calendar c){
        return c == null ? null : c.getTime().getTime();
    }
}