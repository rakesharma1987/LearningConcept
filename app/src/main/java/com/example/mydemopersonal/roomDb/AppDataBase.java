package com.example.mydemopersonal.roomDb;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

//@Database(entities = {User.class}, version = 1)
@Database(entities = {User.class}, version = 2)
public abstract class AppDataBase  extends RoomDatabase {
    private static AppDataBase instance;
    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE User ADD COLUMN mName text");
        }
    };
    public static AppDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "userdatabse")
                    .allowMainThreadQueries() // for disable main thread
//                    .fallbackToDestructiveMigration()
//                    .fallbackToDestructiveMigrationOnDowngrade()
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }
        return instance;
    }
    public abstract UserDao userDao();
}
