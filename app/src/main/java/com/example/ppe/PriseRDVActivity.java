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

    private AutoCompleteTextView spinnerCoiffeur, spinnerPrestation;
    private EditText etDate, etHeure;
    private RecyclerView recyclerViewSalons;
    private SalonAdapter salonAdapter;
    private final Calendar calendar = Calendar.getInstance();
    private List<Salon> salonList;
    private Salon selectedSalon; // Pour stocker le salon sélectionné

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

        // Initialisation de la liste des salons
        salonList = new ArrayList<>();
        salonList.add(new Salon("ERIC-STIPA", "7 Bd Heurteloup, 37000 Tours"));
        salonList.add(new Salon("CARPY Coiffeur Coloriste", "20 Rue Nationale, 37000 Tours"));
        salonList.add(new Salon("Addict Paris Coiffure", "21 Pl. Jean Jaurès, 37000 Tours"));
        salonList.add(new Salon("Atomic Hair Coiffure Mixte", "13 Rue Georges Clemenceau, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("Lounge hair", "17 Rue du Président Wilson, 41200 Romorantin-Lanthenay"));
        salonList.add(new Salon("Styl'hair by Océane", "12 Pl. de la Paix, 41200 Romorantin-Lanthenay"));

        salonAdapter = new SalonAdapter(salonList);
        recyclerViewSalons.setAdapter(salonAdapter);

        salonAdapter.setOnSalonClickListener(position -> {
            selectedSalon = salonList.get(position);
            Toast.makeText(PriseRDVActivity.this, "Salon sélectionné: " + selectedSalon.getName(), Toast.LENGTH_SHORT).show();
        });

        // Adapter pour les coiffeurs
        ArrayAdapter<String> coiffeurAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Mr DUPONT Jean", "Mme MIME Isabelle", "Mme JAQUETOT Catherine"});
        spinnerCoiffeur.setAdapter(coiffeurAdapter);

        // Adapter pour les prestations
        ArrayAdapter<String> prestationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                new String[]{"Prestation 1", "Prestation 2", "Prestation 3"});
        spinnerPrestation.setAdapter(prestationAdapter);

        // Sélecteurs de date et heure
        etDate.setOnClickListener(v -> showDatePicker());
        etHeure.setOnClickListener(v -> showTimePicker());

        // Bouton de confirmation
        btnConfirmerRDV.setOnClickListener(v -> {
            String coiffeur = spinnerCoiffeur.getText().toString();
            String prestation = spinnerPrestation.getText().toString();
            String date = etDate.getText().toString();
            String heure = etHeure.getText().toString();

            if (coiffeur.isEmpty() || prestation.isEmpty() || date.isEmpty() || heure.isEmpty()) {
                Toast.makeText(PriseRDVActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else if (selectedSalon == null) {
                Toast.makeText(PriseRDVActivity.this, "Veuillez sélectionner un salon", Toast.LENGTH_SHORT).show();
            } else {
                if (!isValidDate(date)) {
                    Toast.makeText(PriseRDVActivity.this, "Date invalide", Toast.LENGTH_SHORT).show();
                } else if (!isValidTime(heure)) {
                    Toast.makeText(PriseRDVActivity.this, "Heure invalide", Toast.LENGTH_SHORT).show();
                } else {
                    if (isTimeAfterClosing(heure)) {
                        Toast.makeText(PriseRDVActivity.this, "Les réservations ne sont pas possibles après 19h.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent confirmationIntent = new Intent(PriseRDVActivity.this, ConfirmationRDVActivity.class);
                        confirmationIntent.putExtra("coiffeur", coiffeur);
                        confirmationIntent.putExtra("prestation", prestation);
                        confirmationIntent.putExtra("date", date);
                        confirmationIntent.putExtra("heure", heure);
                        confirmationIntent.putExtra("salon", selectedSalon.getName());
                        confirmationIntent.putExtra("adresse", selectedSalon.getAddress());
                        startActivity(confirmationIntent);
                    }
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

    private boolean isTimeAfterClosing(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        return hour >= 19;
    }
}