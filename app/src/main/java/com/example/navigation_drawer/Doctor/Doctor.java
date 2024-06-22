// Doctor.java
package com.example.navigation_drawer.Doctor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctor_table")
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String specialization;
    private String photo;

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
