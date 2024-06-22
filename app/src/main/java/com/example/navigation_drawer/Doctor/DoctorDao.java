// DoctorDao.java
package com.example.navigation_drawer.Doctor;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoctorDao {
    @Insert
    void insert(Doctor doctor);

    @Query("SELECT * FROM doctor_table")
    List<Doctor> getAllDoctors();

    @Query("SELECT * FROM doctor_table WHERE id = :doctorId")
    Doctor getDoctorById(int doctorId);





}

