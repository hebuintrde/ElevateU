package com.example.navigation_drawer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.navigation_drawer.Doctor.Doctor;
import com.example.navigation_drawer.Doctor.DoctorDao;
import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserDao;
import com.example.navigation_drawer.Message.Message;
import com.example.navigation_drawer.Message.MessageDao;

@Database(entities = {User.class, Doctor.class, Message.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract DoctorDao doctorDao();
    public abstract MessageDao messageDao();

    private static volatile MyDatabase INSTANCE;

    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class, "app_database")
                            .fallbackToDestructiveMigration() // This is needed when changing the schema
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
