package com.example.ppe;

import android.location.Address;

public class Coiffeur {
    private String nom;
    private String prenom;


    public Coiffeur(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;

    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}