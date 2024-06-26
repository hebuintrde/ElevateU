package com.example.navigation_drawer.User;// UserRepository.java

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.navigation_drawer.MyDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




/**
 * Repository class that abstracts access to multiple data sources.
 * This class is responsible for handling data operations for User objects.
 * It provides a clean API to the rest of the application for app data.
 */

public class UserRepository {
    private UserDao userDao;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UserRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        userDao = db.userDao();
    }

    public void insert(final User user) {
        executorService.execute(() -> userDao.insert(user));
    }

    /**
     * Checks if an email exists in the database.
     *
     * @param email The email to check for existence.
     * @return LiveData object containing the result of the email existence check.
     */

    public LiveData<Integer> isEmailExists(String email) {
        return userDao.isEmailExists(email);
    }

    public User getUser(String email, String password) {
        return userDao.getUser(email, password);
    }
}
