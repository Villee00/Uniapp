package com.example.uniapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {Uni.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class uniDatabase extends RoomDatabase {
     public abstract UniDao uniDao();

     private static uniDatabase instance;

     /**
      * Method to check if database instance is already created.
      * If not it will put the given context to be main instance
      *
      * @param context current context to be used in whole program
      * */
     public static uniDatabase getInstance(Context context) {
          if (instance == null) {
               synchronized (uniDatabase.class) {
                    if (instance == null) {
                         instance = Room.databaseBuilder(context.getApplicationContext(),
                                 uniDatabase.class, "unet")
                                 .allowMainThreadQueries()
                                 .build();
                    }
               }
          }
          return instance;
     }

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
