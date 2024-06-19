package com.example.navigation_drawer.User;// UserViewModel.java
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class  UserViewModel extends AndroidViewModel {
    private UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public User getUser(String email, String password) {
        return repository.getUser(email, password);
    }

    public LiveData<Integer> isEmailExists(String email) {
        return repository.isEmailExists(email);
    }
}
