package com.example.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText cgpa,iq,profile_score;
    Button predict;
    TextView result;
    String url = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cgpa = findViewById(R.id.cgpa);
        iq = findViewById(R.id.iq);
        profile_score = findViewById(R.id.profile_score);
        predict = findViewById(R.id.predict);
        result = findViewById(R.id.result);
        predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hit the API -> Volley
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String data = jsonObject.getString("placement");
                                    if(data.equals("1")){
                                        result.setText("Placement Hoga");
                                    }else{
                                        result.setText("Placement Nahi Hoga");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                    @Override
                    protected Map getParams(){
                        Map params = new HashMap();
                        params.put("cgpa",cgpa.getText().toString());
                        params.put("iq",iq.getText().toString());
                        params.put("profile_score",profile_score.getText().toString());
                        return params;
                    }
                };
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);
            }
        });
    }
}
