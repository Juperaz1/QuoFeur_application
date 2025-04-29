package com.example.ppe;

import android.location.Address;

public class Coiffeur {
    private String nom;
    private String prenom;
    private String specialite;

    public Coiffeur(String nom, String prenom, String specialite) {
        this.nom = nom;
        this.prenom = prenom;
        this.specialite = specialite;
    }

    // Getters
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getSpecialite() { return specialite; }

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}