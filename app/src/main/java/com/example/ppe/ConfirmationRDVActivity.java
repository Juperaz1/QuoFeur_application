package com.example.ppe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ConfirmationRDVActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_rdv);

        TextView confirmationTextView = findViewById(R.id.confirmationMessage);
        Button btnRetour = findViewById(R.id.btnRetour);


        String coiffeur = getIntent().getStringExtra("coiffeur");
        String prestation = getIntent().getStringExtra("prestation");
        String date = getIntent().getStringExtra("date");
        String heure = getIntent().getStringExtra("heure");
        String salon = getIntent().getStringExtra("salon");
        String adresse = getIntent().getStringExtra("adresse");


        String message = "Votre rendez-vous avec " + coiffeur +
                "\nPour: " + prestation +
                "\nLe: " + date + " à " + heure +
                "\nAu salon: " + salon +
                "\nAdresse: " + adresse +
                "\n\nest confirmé !";
        confirmationTextView.setText(message);


        btnRetour.setOnClickListener(v -> {
            Intent intent = new Intent(ConfirmationRDVActivity.this, MenuActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        });
    }
}