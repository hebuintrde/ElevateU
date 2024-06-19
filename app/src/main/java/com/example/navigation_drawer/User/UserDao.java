package com.example.navigation_drawer.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);



    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT * FROM user_table WHERE email = :email")
    User findUserByEmail(String email);

    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    User getUser(String email, String password);


    @Query("SELECT * FROM user_table WHERE email = :email AND password = :password")
    User findUserByEmailAndPassword(String email, String password);


    @Query("SELECT COUNT(*) FROM user_table WHERE email = :email")
    LiveData<Integer> isEmailExists(String email);


   /* @Query("SELECT EXISTS(SELECT * FROM usertable WHERE email = :email)")
    boolean isEmailTaken(String email);

    @Query("SELECT EXISTS(SELECT * FROM usertable WHERE email= :email AND password = :password)")
    boolean login(String email, String password);
    */
}

