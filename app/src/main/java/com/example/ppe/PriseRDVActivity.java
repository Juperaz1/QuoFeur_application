package com.example.ppe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PriseRDVActivity extends AppCompatActivity {
    private RecyclerView recyclerViewSalons;
    private SalonAdapter salonAdapter;
    private List<Salon> salonList;
    private Button btnSuivant;
    private Salon salonSelectionne; // Nouvelle variable pour stocker le salon sélectionné

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priserdv);

        recyclerViewSalons = findViewById(R.id.recyclerViewSalons);
        btnSuivant = findViewById(R.id.btnSuivant);
        btnSuivant.setEnabled(false);

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
            salonSelectionne = salonList.get(position); // Mettre à jour le salon sélectionné
            btnSuivant.setEnabled(true); // Activer le bouton une fois qu'un salon est sélectionné
        });

        // Configurer l'OnClickListener du bouton "Suivant" une seule fois
        btnSuivant.setOnClickListener(v -> {
            if (salonSelectionne != null) {
                Intent intent = new Intent(PriseRDVActivity.this, ChoixCoiffeurActivity.class);
                intent.putExtra("selectedSalonName", salonSelectionne.getName());
                intent.putExtra("selectedSalonAddress", salonSelectionne.getLocation());
                startActivity(intent);
            } else {
                Toast.makeText(PriseRDVActivity.this, "Veuillez sélectionner un salon", Toast.LENGTH_SHORT).show();
            }
        });
    }
}