package com.example.ppe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

    private AutoCompleteTextView spinnerCoiffeur, spinnerPrestation;
    private EditText etDate, etHeure;
    private RecyclerView recyclerViewSalons;
    private SalonAdapter salonAdapter;
    private final Calendar calendar = Calendar.getInstance();
    private List<Salon> salonList; // Declare salonList here

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
        salonList = new ArrayList<>();
        salonList.add(new Salon("Salon 1", "Paris"));
        salonList.add(new Salon("Salon 2", "Lyon"));
        salonList.add(new Salon("Salon 3", "Marseille"));

        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

        // Set the click listener for the salon items
        salonAdapter.setOnSalonClickListener(position -> {
            Salon clickedSalon = salonList.get(position);
            Toast.makeText(PriseRDVActivity.this, "Salon sélectionné: " + clickedSalon.getName(), Toast.LENGTH_SHORT).show();
            // Here you might want to store the selected salon and proceed with the RDV booking
        });

        // Configuration des AutoCompleteTextView avec des données
        ArrayAdapter<String> coiffeurAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Coiffeur 1", "Coiffeur 2", "Coiffeur 3"});
        spinnerCoiffeur.setAdapter(coiffeurAdapter);

        ArrayAdapter<String> prestationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Prestation 1", "Prestation 2", "Prestation 3"});
        spinnerPrestation.setAdapter(prestationAdapter);

        // Date picker
        etDate.setOnClickListener(v -> showDatePicker());

        // Time picker
        etHeure.setOnClickListener(v -> showTimePicker());

        // Listener du bouton Confirmer RDV
        btnConfirmerRDV.setOnClickListener(v -> {
            // Récupérer les valeurs des champs
            String coiffeur = spinnerCoiffeur.getText().toString();
            String prestation = spinnerPrestation.getText().toString();
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
                    Toast.makeText(PriseRDVActivity.this, "RDV confirmé avec " + coiffeur + " le " + date + " à " + heure, Toast.LENGTH_SHORT).show();
                    // Here you would typically save the appointment details to a database or send them to a server.
                }
            }
        });
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, month + 1, year);
                    etDate.setText(selectedDate);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void showTimePicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                    etHeure.setText(selectedTime);
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private boolean isValidDate(String date) {
        return date.matches("\\d{2}/\\d{2}/\\d{4}");
    }

    private boolean isValidTime(String time) {
        return time.matches("\\d{2}:\\d{2}");
    }
}