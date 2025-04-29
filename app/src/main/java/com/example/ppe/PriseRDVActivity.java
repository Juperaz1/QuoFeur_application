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


        salonList = new ArrayList<>();
        salonList.add(new Salon("ERIC-STIPA", "7 Bd Heurteloup, 37000 Tours"));
        salonList.add(new Salon("De Meche Avec Vous", "107 Rue Lakanal, 37000 Tours"));
        salonList.add(new Salon("CARPY Coiffeur Coloriste", "20 Rue Nationale, 37000 Tours"));
        salonList.add(new Salon("Coiff&Co","5 Pl. Neuve, 37000 Tours"));
        salonList.add(new Salon("Le coiffeur de monsieur","82 Rue Georges Clemenceau, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("Elle & Lui", "39 bis Fbg d'Orléans, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("MD Coiff", "4 Pl. de la Paix, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("Lounge hair", "17 Rue du Président Wilson, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("Philippe Friaud Coiffure", "12 Rue Georges Clemenceau, 41200 Romorantin-Lanthenay"));


        recyclerViewSalons.setLayoutManager(new LinearLayoutManager(this));
        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

        salonAdapter.setOnSalonClickListener(position -> {
            Salon selectedSalon = salonList.get(position);
            btnConfirmerRDV.setEnabled(true);

            btnConfirmerRDV.setOnClickListener(v -> {
                Intent intent = new Intent(PriseRDVActivity.this, ChoixCoiffeurActivity.class);
                intent.putExtra("selectedSalonName", selectedSalon.getName());
                intent.putExtra("selectedSalonAddress", selectedSalon.getLocation());
                startActivity(intent);
            });
        });
    }
}