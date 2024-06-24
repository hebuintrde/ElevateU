package com.example.navigation_drawer.Maps;


import com.google.android.gms.maps.model.LatLng;

public class Clinic {
    private LatLng location;
    private String name;
    private String description;

    //Constructor and getter Methods for clinics' attributes

    public Clinic(LatLng location, String name, String description) {
        this.location = location;
        this.name = name;
        this.description = description;
    }

    public LatLng getLocation() {

        return location;
    }

    public String getName() {

        return name;
    }

    public String getDescription() {

        return description;
    }
}
