package com.example.ppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button btnProfil = findViewById(R.id.btnProfil);
        Button btnPriseRDV = findViewById(R.id.btnPriseRDV);


        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer l'ID de l'utilisateur depuis les SharedPreferences
                SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                int userId = prefs.getInt("user_id", -1); // -1 est la valeur par défaut si l'ID n'est pas trouvé

                if (userId != -1) {
                    // Créer un Intent pour démarrer ProfilActivity
                    Intent intent = new Intent(MenuActivity.this, com.example.ppe.ProfilActivity.class);
                    // Ajouter l'ID de l'utilisateur à l'Intent
                    intent.putExtra("USER_ID", userId);
                    // Démarrer ProfilActivity
                    startActivity(intent);
                } else {
                    // Gérer le cas où l'ID de l'utilisateur n'a pas été trouvé dans les SharedPreferences
                    // Peut-être rediriger l'utilisateur vers la page de connexion ou afficher un message d'erreur
                    android.widget.Toast.makeText(MenuActivity.this, "ID utilisateur non trouvé.", android.widget.Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnPriseRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, com.example.ppe.PriseRDVActivity.class);
                startActivity(intent);
            }
        });
    }
}

