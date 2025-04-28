package com.example.ppe;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        TextView tvNom = findViewById(R.id.tvNom);
        TextView tvPrenom = findViewById(R.id.tvPrenom);
        TextView tvPoints = findViewById(R.id.tvPoints);
        TextView tvHistorique = findViewById(R.id.tvHistorique);


        tvNom.setText("Nom : " + tvNom);
        tvPrenom.setText("Prénom : " + tvPrenom);
        tvPoints.setText("Points : " + tvPoints);
        tvHistorique.setText("Historique : " + tvHistorique);
    }
}
