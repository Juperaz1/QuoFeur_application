package com.example.ppe;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationRDVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_rdv);

        TextView confirmationTextView = findViewById(R.id.confirmationMessage);

        // Récupérer les informations passées depuis PriseRDVActivity
        String coiffeur = getIntent().getStringExtra("coiffeur");
        String prestation = getIntent().getStringExtra("prestation");
        String date = getIntent().getStringExtra("date");
        String heure = getIntent().getStringExtra("heure");

        // Afficher le message de confirmation avec les détails
        String message = "Votre rendez-vous avec " + coiffeur + " pour " + prestation + " le " + date + " à " + heure + " est confirmé !";
        confirmationTextView.setText(message);
    }
}