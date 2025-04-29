package com.example.ppe;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ChoixCoiffeurActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCoiffeurs;
    private CoiffeurAdapter coiffeurAdapter;
    private List<Coiffeur> coiffeurList;
    private String salonName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_coiffeur);

        salonName = getIntent().getStringExtra("selectedSalonName");
        String salonAddress = getIntent().getStringExtra("selectedSalonAddress");


        TextView title = findViewById(R.id.textViewTitle);
        title.setText("Coiffeurs disponibles chez " + salonName);


        recyclerViewCoiffeurs = findViewById(R.id.recyclerViewCoiffeurs);
        recyclerViewCoiffeurs.setLayoutManager(new LinearLayoutManager(this));


        coiffeurList = getCoiffeursForSalon(salonName);

        coiffeurAdapter = new CoiffeurAdapter(coiffeurList);
        recyclerViewCoiffeurs.setAdapter(coiffeurAdapter);
    }

    private List<Coiffeur> getCoiffeursForSalon(String salonName) {
        List<Coiffeur> coiffeurs = new ArrayList<>();


        if(salonName.equals("ERIC-STIPA")) {
            coiffeurs.add(new Coiffeur("Jean", "Dupont", "Coupe homme"));
            coiffeurs.add(new Coiffeur("Sophie", "Martin", "Coloration"));
        }
        else if(salonName.equals("CARPY Coiffeur Coloriste")) {
            coiffeurs.add(new Coiffeur("Luc", "Bernard", "Barbier"));
            coiffeurs.add(new Coiffeur("Marie", "Petit", "Coupe femme"));
        }


        return coiffeurs;
    }
}