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


        btnProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MenuActivity.this, com.example.ppe.ProfilActivity.class);
                startActivity(intent);
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
