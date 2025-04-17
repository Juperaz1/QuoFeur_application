package com.example.ppe;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserDetailsActivity extends AppCompatActivity {
    RequestQueue rq;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);
        rq= Volley.newRequestQueue(this);
        Intent i= getIntent();
        token=i.getStringExtra("token");
        Log.i("HELLOJWT", "token "+token);
        requestDetails();
    }

    public void requestDetails(){

        String url="http://localhost/~justine.loiseau/PPE-/public/";
        StringRequest req =new StringRequest(Request.Method.GET,url,this::processDetails,this::handleErrors){
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //params.put("Authorization", "Bearer "+ token);
                params.put("Authorization",  token);
                return params;
            }
        };
        rq.add(req);
    }

    public void processDetails(String response){
        try{
            JSONObject joData=new JSONObject(response).getJSONObject("data").getJSONObject("profile").getJSONObject("data");
            TextView tvPs,tvM,tvPh;
            tvPs=findViewById(R.id.etFirstName);
            tvM=findViewById(R.id.etLastName);
            tvPh=findViewById(R.id.etPassword);
            tvPs.setText(joData.getString("name"));
            tvPh.setText(joData.getString("phone_no"));
            tvM.setText(joData.getString("email"));
        }catch (JSONException x){
            Toast.makeText(this,"JSON PARSE ERROR",Toast.LENGTH_LONG).show();
            Log.e("HELLOJWT","JSON PARSE ERROR: "+response,x);
        }

    }


    public void handleErrors(Throwable t){
        Toast.makeText(this,"SERVERSIDE PROBLEM",Toast.LENGTH_LONG).show();
        Log.e("HELLOJWT","SERVERSIDE BUG",t);
    }

}
