package com.example.ppe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PriseRDVActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSalons;
    private SalonAdapter salonAdapter;
    private List<Salon> salonList;
    private Button btnConfirmerRDV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priserdv);

        recyclerViewSalons = findViewById(R.id.recyclerViewSalons);
        btnConfirmerRDV = findViewById(R.id.btnSuivant);
        btnConfirmerRDV.setEnabled(false);

        // Initialisation de la liste des salons
        salonList = new ArrayList<>();
        salonList.add(new Salon("ERIC-STIPA", "7 Bd Heurteloup, 37000 Tours"));
        salonList.add(new Salon("CARPY Coiffeur Coloriste", "20 Rue Nationale, 37000 Tours"));
        // ... autres salons

        // Configuration du RecyclerView
        recyclerViewSalons.setLayoutManager(new LinearLayoutManager(this));
        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

        salonAdapter.setOnSalonClickListener(position -> {
            Salon selectedSalon = salonList.get(position);
            btnConfirmerRDV.setEnabled(true);

            // Stocker le salon sélectionné pour le passer à l'activité suivante
            btnConfirmerRDV.setOnClickListener(v -> {
                Intent intent = new Intent(PriseRDVActivity.this, ChoixCoiffeurActivity.class);
                intent.putExtra("selectedSalonName", selectedSalon.getName());
                intent.putExtra("selectedSalonAddress", selectedSalon.getLocation());
                startActivity(intent);
            });
        });
    }
}