package com.example.mydemopersonal.roomDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDataBase  extends RoomDatabase {
    private static AppDataBase instance;
    public static AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "userdatabse").build();
        }
        return instance;
    }
    public abstract UserDao userDao();
}
