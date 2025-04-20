package com.example.ppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText etFirstName, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etFirstName = findViewById(R.id.etFirstName);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }


    private void loginUser() {

        String url = "http://localhost/PPE-/public/login";

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            try {

                JSONObject obj = new JSONObject(response);
                String token = obj.getJSONObject("data").getString("token");
                int userId = obj.getJSONObject("data").getInt("id_utilisateur");


                SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                prefs.edit().putString("jwt", token).putInt("id_utilisateur", userId).apply();


                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, "Erreur parsing de la réponse", Toast.LENGTH_SHORT).show();
            }
        }, error -> {

            Toast.makeText(MainActivity.this, "Login échoué, vérifiez vos informations", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();
                params.put("login", etFirstName.getText().toString());
                params.put("password", etPassword.getText().toString());
                return params;
            }
        };

        // Ajouter la requête à la file d'attente de Volley pour exécution
        Volley.newRequestQueue(this).add(request);
    }
}
