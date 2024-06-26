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

 /**
 * MyDatabase is the main database class for the application.
 * It is an abstract class that extends RoomDatabase.
 * This class provides access to the DAOs for the entities.
 */
@Database(entities = {User.class, Doctor.class, Message.class}, version = 2, exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

    /**
     * Provides access to the UserDao.
     *
     * @return the UserDao.
     */
    public abstract UserDao userDao();

    /**
     * Provides access to the DoctorDao.
     *
     * @return the DoctorDao.
     */
    public abstract DoctorDao doctorDao();

    /**
     * Provides access to the MessageDao.
     *
     * @return the MessageDao.
     */
    public abstract MessageDao messageDao();

    // Singleton instance of MyDatabase
    private static volatile MyDatabase INSTANCE;

    /**
     * Gets the singleton instance of MyDatabase.
     *
     * @param context the application context.
     * @return the singleton instance of MyDatabase.
     */
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
