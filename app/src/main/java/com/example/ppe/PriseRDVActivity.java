package com.example.ppe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PriseRDVActivity extends AppCompatActivity {

    private Spinner spinnerCoiffeur, spinnerPrestation;
    private EditText etDate, etHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priserdv);

        // Vérifier que ces IDs sont corrects dans le fichier XML
        spinnerCoiffeur = findViewById(R.id.spinnerCoiffeur);
        spinnerPrestation = findViewById(R.id.spinnerPrestation);
        etDate = findViewById(R.id.etDate);
        etHeure = findViewById(R.id.etHeure);
        Button btnConfirmerRDV = findViewById(R.id.btnConfirmerRDV);

        // Implémenter la logique du bouton de confirmation
        btnConfirmerRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String coiffeur = spinnerCoiffeur.getSelectedItem().toString();
                String prestation = spinnerPrestation.getSelectedItem().toString();
                String date = etDate.getText().toString();
                String heure = etHeure.getText().toString();

                if (coiffeur.isEmpty() || prestation.isEmpty() || date.isEmpty() || heure.isEmpty()) {
                    Toast.makeText(PriseRDVActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Logic for confirming the appointment
                    Toast.makeText(PriseRDVActivity.this, "RDV confirmé avec " + coiffeur, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
