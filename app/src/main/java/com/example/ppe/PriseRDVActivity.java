package com.example.ppe;

import android.os.Bundle;
import android.view.View;
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

        spinnerCoiffeur = findViewById(R.id.spinnerCoiffeur);
        spinnerPrestation = findViewById(R.id.spinnerPrestation);
        etDate = findViewById(R.id.etDate);
        etHeure = findViewById(R.id.etHeure);
        Button btnConfirmerRDV = findViewById(R.id.btnConfirmerRDV);
        recyclerViewSalons = findViewById(R.id.recyclerViewSalons);

        // Initialisation de la RecyclerView
        recyclerViewSalons.setLayoutManager(new LinearLayoutManager(this));


        List<Salon> salonList = new ArrayList<>();
        salonList.add(new Salon("Salon 1", "Paris"));
        salonList.add(new Salon("Salon 2", "Lyon"));
        salonList.add(new Salon("Salon 3", "Marseille"));


        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

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
                    Toast.makeText(PriseRDVActivity.this, "RDV confirmé avec " + coiffeur, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
