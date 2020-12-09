package com.example.uniapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Uni.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class uniDatabase extends RoomDatabase {
     public abstract UniDao uniDao();

     /*
     *         uniDatabase db = Room.databaseBuilder(getApplicationContext(), uniDatabase.class, "unet")
                .allowMainThreadQueries()
                .build();

        db.uniDao().addUni(new Uni());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        db.uniDao().loadUniDates(cal, Calendar.getInstance());
     *
     * */
}
