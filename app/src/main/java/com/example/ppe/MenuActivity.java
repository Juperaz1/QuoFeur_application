package com.example.ppe;

import android.content.Intent;
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

        // Quand on clique sur le bouton Profil, on redirige vers ProfilActivity
        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige vers ProfilActivity
                Intent intent = new Intent(MenuActivity.this, com.example.ppe.ProfilActivity.class);
                startActivity(intent);
            }
        });

        // Quand on clique sur le bouton Prise de RDV, on redirige vers PriseRDVActivity
        btnPriseRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirige vers PriseRDVActivity
                Intent intent = new Intent(MenuActivity.this, com.example.ppe.PriseRDVActivity.class);
                startActivity(intent);
            }
        });
    }
}
