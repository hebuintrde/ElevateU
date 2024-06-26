// Doctor.java
package com.example.navigation_drawer.Doctor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Doctor entity representing a table in the Room database.
 */
@Entity(tableName = "doctor_table")
public class Doctor {

    /**
     * Primary key for the Doctor entity, auto-generated.
     */
    @PrimaryKey(autoGenerate = true)
    private int id;

    /**
     * Name of the doctor.
     */
    private String name;

    /**
     * Specialization of the doctor.
     */
    private String specialization;


    private String photo;

    /**
     * Constructor for the Doctor class.
     *
     * @param name the name of the doctor.
     * @param specialization the specialization of the doctor.
     * @param photo the photo of the doctor.
     */
    public Doctor(String name, String specialization, String photo) {
        this.name = name;
        this.specialization = specialization;
        this.photo = photo;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getSpecialization() {
        return specialization;
    }


    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }


    public String getPhoto() {
        return photo;
    }


    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
