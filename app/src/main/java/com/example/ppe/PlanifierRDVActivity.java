package com.example.ppe;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class PlanifierRDVActivity extends AppCompatActivity {

    private TextView dateTextView;
    private TextView heureTextView;
    private Button choisirDateButton;
    private Button choisirHeureButton;
    private Button btnConfirmerRDV;
    private EditText prestationEditText; // Nouveau champ pour la prestation

    private String coiffeur;
    private String salon;
    private String adresse;
    private String selectedDate;
    private String selectedHeure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planifier_rdv);

        dateTextView = findViewById(R.id.dateTextView);
        heureTextView = findViewById(R.id.heureTextView);
        choisirDateButton = findViewById(R.id.choisirDateButton);
        choisirHeureButton = findViewById(R.id.choisirHeureButton);
        btnConfirmerRDV = findViewById(R.id.btnConfirmerRDV);
        prestationEditText = findViewById(R.id.editTextPrestation);

        coiffeur = getIntent().getStringExtra("coiffeur");
        salon = getIntent().getStringExtra("salon");
        adresse = getIntent().getStringExtra("adresse");

        choisirDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherDatePickerDialog();
            }
        });

        choisirHeureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherTimePickerDialog();
            }
        });

        btnConfirmerRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prestationChoisie = prestationEditText.getText().toString().trim();

                if (selectedDate != null && selectedHeure != null && !prestationChoisie.isEmpty()) {
                    Intent intent = new Intent(PlanifierRDVActivity.this, ConfirmationRDVActivity.class); // Supposons que vous ayez une activité de confirmation
                    intent.putExtra("coiffeur", coiffeur);
                    intent.putExtra("prestation", prestationChoisie);
                    intent.putExtra("date", selectedDate);
                    intent.putExtra("heure", selectedHeure);
                    intent.putExtra("salon", salon);
                    intent.putExtra("adresse", adresse);
                    startActivity(intent);
                } else {
                    if (prestationChoisie.isEmpty()) {
                        prestationEditText.setError("Veuillez entrer le type de prestation");
                    }
                    if (selectedDate == null) {
                        // Optionally show a message
                    }
                    if (selectedHeure == null) {
                        // Optionally show a message
                    }
                }
            }
        });

        String prestationRecue = getIntent().getStringExtra("prestation");
        if (prestationRecue != null && !prestationRecue.isEmpty()) {
            prestationEditText.setText(prestationRecue);
        }
    }

    private void afficherDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int annee = calendar.get(Calendar.YEAR);
        int mois = calendar.get(Calendar.MONTH);
        int jour = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = dayOfMonth + "/" + (monthOfYear + 1) + "/" + year;
                        dateTextView.setText("Date sélectionnée: " + selectedDate);
                    }
                },
                annee,
                mois,
                jour
        );
        datePickerDialog.show();
    }

    private void afficherTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int heure = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedHeure = String.format("%02d:%02d", hourOfDay, minute);
                        heureTextView.setText("Heure sélectionnée: " + selectedHeure);
                    }
                },
                heure,
                minute,
                true
        );
        timePickerDialog.show();
    }
}