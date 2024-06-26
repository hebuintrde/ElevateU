package com.example.navigation_drawer.User;// UserViewModel.java
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * ViewModel class that is responsible for preparing and managing the data for an Activity or a Fragment.
 * It handles the communication of the Activity / Fragment with the data sources (UserRepository).
 */
public class  UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insert(User user) {
        repository.insert(user);
    }


    /**
     * Retrieves a user from the database based on email and password via the repository.
     *
     * @param email    The email of the user.
     * @param password The password of the user.
     * @return The user object if found, null otherwise.
     */
    public User getUser(String email, String password) {
        return repository.getUser(email, password);
    }

    public LiveData<Integer> isEmailExists(String email) {
        return repository.isEmailExists(email);
    }
}
