package com.example.ppe;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PriseRDVActivity extends AppCompatActivity {

    private Spinner spinnerCoiffeur, spinnerPrestation;
    private EditText etDate, etHeure;
    private RecyclerView recyclerViewSalons;
    private SalonAdapter salonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priserdv);

        // Initialisation des vues
        spinnerCoiffeur = findViewById(R.id.spinnerCoiffeur);
        spinnerPrestation = findViewById(R.id.spinnerPrestation);
        etDate = findViewById(R.id.etDate);
        etHeure = findViewById(R.id.etHeure);
        Button btnConfirmerRDV = findViewById(R.id.btnConfirmerRDV);
        recyclerViewSalons = findViewById(R.id.recyclerViewSalons);

        // Initialisation de la RecyclerView
        recyclerViewSalons.setLayoutManager(new LinearLayoutManager(this));

        // Liste des salons
        List<Salon> salonList = new ArrayList<>();
        salonList.add(new Salon("Salon 1", "Paris"));
        salonList.add(new Salon("Salon 2", "Lyon"));
        salonList.add(new Salon("Salon 3", "Marseille"));

        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

        // Configuration des Spinners avec des données
        ArrayAdapter<String> coiffeurAdapter = new ArrayAdapter<>(PriseRDVActivity.this, android.R.layout.simple_spinner_item, new String[]{"Coiffeur 1", "Coiffeur 2", "Coiffeur 3"});
        coiffeurAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCoiffeur.setAdapter(coiffeurAdapter);

        ArrayAdapter<String> prestationAdapter = new ArrayAdapter<>(PriseRDVActivity.this, android.R.layout.simple_spinner_item, new String[]{"Prestation 1", "Prestation 2", "Prestation 3"});
        prestationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPrestation.setAdapter(prestationAdapter);

        // Listener du bouton Confirmer RDV
        btnConfirmerRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer les valeurs des champs
                String coiffeur = spinnerCoiffeur.getSelectedItem().toString();
                String prestation = spinnerPrestation.getSelectedItem().toString();
                String date = etDate.getText().toString();
                String heure = etHeure.getText().toString();

                // Vérifier si les champs sont remplis
                if (coiffeur.isEmpty() || prestation.isEmpty() || date.isEmpty() || heure.isEmpty()) {
                    Toast.makeText(PriseRDVActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                } else {
                    // Vérification de la validité de la date et de l'heure
                    if (!isValidDate(date)) {
                        Toast.makeText(PriseRDVActivity.this, "Date invalide", Toast.LENGTH_SHORT).show();
                    } else if (!isValidTime(heure)) {
                        Toast.makeText(PriseRDVActivity.this, "Heure invalide", Toast.LENGTH_SHORT).show();
                    } else {
                        // Affichage du message de confirmation
                        Toast.makeText(PriseRDVActivity.this, "RDV confirmé avec " + coiffeur, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    // Méthode pour vérifier si la date est valide (format simple "dd/MM/yyyy")
    private boolean isValidDate(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }


    private boolean isValidTime(String time) {
        return time.matches("\\d{2}:\\d{2}");
    }
}

