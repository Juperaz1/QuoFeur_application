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
            coiffeurs.add(new Coiffeur("Dupont-Aignen", "Céline"));
            coiffeurs.add(new Coiffeur("Eltorado", "Martin"));
            coiffeurs.add(new Coiffeur("Sloan","Marc"));
            coiffeurs.add(new Coiffeur("Stipa","Eric"));
        }
        else if(salonName.equals("De Meche Avec Vous")){
            coiffeurs.add(new Coiffeur("Lopez", "Jaques"));
            coiffeurs.add(new Coiffeur("Reyes", "Raven"));
            coiffeurs.add(new Coiffeur("Grey","Lexie"));
            coiffeurs.add(new Coiffeur("Adeulo","Thomas"));
        }
        else if(salonName.equals("CARPY Coiffeur Coloriste")) {
            coiffeurs.add(new Coiffeur("Lefebvre", "Jennyfer"));
            coiffeurs.add(new Coiffeur("Moreau", "Angèle"));
            coiffeurs.add(new Coiffeur("Levasseur","Liam"));
            coiffeurs.add(new Coiffeur("Hervieu","Madelaine"));
        }
        else if(salonName.equals("Coiff&Co")){
            coiffeurs.add(new Coiffeur("Martinez", "Eude"));
            coiffeurs.add(new Coiffeur("Coll", "Martin"));
            coiffeurs.add(new Coiffeur("Desbourdes","Elise"));
            coiffeurs.add(new Coiffeur("Letourneur","Jeanne"));
        }
        else if(salonName.equals("Le coiffeur de monsieur")) {
            coiffeurs.add(new Coiffeur("Luc", "Bernard"));
            coiffeurs.add(new Coiffeur("Fernandez", "Marie"));
            coiffeurs.add(new Coiffeur("Stevens","Izzie"));
            coiffeurs.add(new Coiffeur("Karev","Alex"));
        }
        else if(salonName.equals("Elle & Lui")){
            coiffeurs.add(new Coiffeur("Mũller", "Maelys"));
            coiffeurs.add(new Coiffeur("Popa", "Amber"));
            coiffeurs.add(new Coiffeur("Carpentier","Lisa"));
            coiffeurs.add(new Coiffeur("SanJosé","Camille"));
        }
        else if(salonName.equals("MD Coiff")) {
            coiffeurs.add(new Coiffeur("Letellier", "Monica"));
            coiffeurs.add(new Coiffeur("Elizabeth", "Smith"));
            coiffeurs.add(new Coiffeur("Delamare","Zoé"));
            coiffeurs.add(new Coiffeur("Webber","Richard"));
        }
        else if(salonName.equals("Lounge hair")){
            coiffeurs.add(new Coiffeur("Leité", "Stephanie"));
            coiffeurs.add(new Coiffeur("Tondut", "Jean"));
            coiffeurs.add(new Coiffeur("Arrent","Ilyess"));
            coiffeurs.add(new Coiffeur("de-mont","Mireille"));
        }
        else if(salonName.equals("Philippe Friaud Coiffure")) {
            coiffeurs.add(new Coiffeur("Preston", "Anne"));
            coiffeurs.add(new Coiffeur("O'Malley", "Georges"));
            coiffeurs.add(new Coiffeur("Sheperd","Derek"));
            coiffeurs.add(new Coiffeur("Yang","Christina"));
        }
        return coiffeurs;
    }
}