package com.example.uremind;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.ConcurrentModificationException;

// Add database entitas
@Database(entities = {MainData.class},version = 1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
    // instance database
    private static RoomDB database;

    // database name
    private static String DATABASE_NAME = "database";

    public synchronized static RoomDB getInstance(Context context){
        if(database == null){
            //inisialisasi database
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //Return status
        return database;
    }

    //Create DAO
    public abstract MainDao mainDao();
}
