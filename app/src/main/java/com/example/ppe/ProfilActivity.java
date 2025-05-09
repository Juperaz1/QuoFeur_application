package com.example.ppe;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfilActivity extends AppCompatActivity {

    private TextView tvNom;
    private TextView tvPrenom;
    private TextView tvPoints;
    private TextView tvHistorique;
    private RequestQueue requestQueue;
    private String url;  // Déclaration de l'URL sans initialisation immédiate
    private static final String TAG = "ProfilActivity";
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Initialisation des vues
        tvNom = findViewById(R.id.tvNom);
        tvPrenom = findViewById(R.id.tvPrenom);
        tvPoints = findViewById(R.id.tvPoints);
        tvHistorique = findViewById(R.id.tvHistorique);

        // Initialisation de la RequestQueue
        requestQueue = Volley.newRequestQueue(this);

        // Récupérer l'ID de l'utilisateur depuis l'intent
        idUser = getIntent().getIntExtra("USER_ID", -1); // -1 comme valeur par défaut si l'ID est absent

        if (idUser == -1) {
            // Gérer le cas où l'ID de l'utilisateur n'est pas disponible
            Log.e(TAG, "ID de l'utilisateur non fourni.");
            displayErrorMessage("ID de l'utilisateur non fourni.");
            return; // Arrêter l'exécution de onCreate()
        }

        Log.d(TAG, "ID de l'utilisateur récupéré depuis l'Intent : " + idUser);

        // Initialiser l'URL avec l'ID de l'utilisateur
        url = "http://192.168.0.111/~justine.loiseau/PPE-/public/api/profil/" + idUser;

        fetchUserProfile();


    }

    private void fetchUserProfile() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d(TAG, "Réponse de l'API complète : " + response.toString(2));
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            String nom = response.getString("nom");
                            String prenom = response.getString("prenom");
                            int points = response.getInt("points");
                            String historique = response.getString("historique");

                            tvNom.setText(String.format("Nom : %s", nom));
                            tvPrenom.setText(String.format("Prénom : %s", prenom));
                            tvPoints.setText(String.format("Points : %d", points));
                            tvHistorique.setText(String.format("Historique : %s", historique));

                        } catch (JSONException e) {
                            Log.e(TAG, "Erreur lors du parsing JSON : " + e.getMessage());
                            displayErrorMessage("Erreur lors de la récupération des données du profil.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Erreur lors de la requête API : " + error.toString());
                displayErrorMessage("Erreur de connexion au serveur.");
            }
        });

        // Ajouter la requête à la RequestQueue
        requestQueue.add(jsonObjectRequest);
    }

    private void displayErrorMessage(String message) {
        tvNom.setText(message);
        tvPrenom.setText("");
        tvPoints.setText("");
        tvHistorique.setText("");
    }
}