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
import android.util.Log;
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

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        String url = "http://192.168.1.30/PPE-/public/login";

        StringRequest request = new StringRequest(Request.Method.POST, url, response -> {
            Log.d("LOGIN_RESPONSE", response);
            try {
                JSONObject obj = new JSONObject(response);
                String token = obj.getString("access_token");
                String message = obj.getString("message");
                JSONObject userObj = obj.getJSONObject("user");
                int userId = userObj.getInt("id_utilisateur");

                if (message.equals("Utilisateur authentifié avec succès")) {
                    SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                    prefs.edit().putString("jwt", token).putInt("user_id", userId).apply();

                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Le login a échoué", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                Toast.makeText(getApplicationContext(), "Erreur de parsing JSON", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }, error -> {
            error.printStackTrace();
            Log.e("VOLLEY_ERROR", "Erreur réseau : " + error.toString());
            Toast.makeText(getApplicationContext(), "Erreur réseau", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<>();
                map.put("pseudo_utilisateur", etFirstName.getText().toString());
                map.put("mdp_utilisateur", etPassword.getText().toString());
                return map;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
}