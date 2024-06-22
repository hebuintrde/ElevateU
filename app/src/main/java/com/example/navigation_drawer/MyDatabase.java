package com.example.navigation_drawer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.navigation_drawer.Doctor.Doctor;
import com.example.navigation_drawer.Doctor.DoctorDao;
import com.example.navigation_drawer.User.User;
import com.example.navigation_drawer.User.UserDao;

@Database(entities = {User.class, Doctor.class}, version = 1, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract DoctorDao doctorDao();

    private static volatile MyDatabase INSTANCE;

    public static MyDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
