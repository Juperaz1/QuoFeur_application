package com.example.ppe;

import android.location.Address;

public class Salon {
    private String name;
    private String address;
    private String location;

    public Salon(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }
    public String getAddress(){
        return address;
    }

    public String getLocation() {
        return location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}