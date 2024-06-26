// DoctorDao.java
package com.example.navigation_drawer.Doctor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * DoctorDao is an interface that defines database operations for the Doctor entity.
 * It is used by the Room persistence library to generate the necessary code for these operations.
 */

@Dao
public interface DoctorDao {
    @Insert
    void insert(Doctor doctor);

    @Query("SELECT * FROM doctor_table")
    List<Doctor> getAllDoctors();

    @Query("SELECT * FROM doctor_table WHERE id = :doctorId")
    Doctor getDoctorById(int doctorId);





}

