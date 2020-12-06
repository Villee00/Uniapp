package com.example.uniapp.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Uni.class}, version = 1, exportSchema = false)
public abstract class uniDatabase extends RoomDatabase {
     public abstract UniDao uniDao();
}
