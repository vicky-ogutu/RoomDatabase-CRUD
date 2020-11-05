package com.example.roomdatabasetutorial;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;



@Database(entities = {student.class}, version = 1, exportSchema = false)
public abstract class studentRoomDatabase extends RoomDatabase {

    private static studentRoomDatabase instance;

    public abstract  studentDao _studentDao();


    static studentRoomDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (studentRoomDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            studentRoomDatabase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }


}
