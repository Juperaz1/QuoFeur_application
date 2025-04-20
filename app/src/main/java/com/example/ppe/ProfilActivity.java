package com.example.ppe;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Trouver les TextViews dans le layout
        TextView tvNom = findViewById(R.id.tvNom);
        TextView tvPrenom = findViewById(R.id.tvPrenom);
        TextView tvPoints = findViewById(R.id.tvPoints);
        TextView tvHistorique = findViewById(R.id.tvHistorique);

        // Simuler des informations utilisateur (ces données peuvent venir de la base de données ou d'un autre endroit)
        String nom = "Dupont";
        String prenom = "Jean";
        String points = "150 points";
        String historique = "Aucun historique";

        // Afficher les informations dans les TextViews
        tvNom.setText("Nom : " + nom);
        tvPrenom.setText("Prénom : " + prenom);
        tvPoints.setText("Points : " + points);
        tvHistorique.setText("Historique : " + historique);
    }
}
