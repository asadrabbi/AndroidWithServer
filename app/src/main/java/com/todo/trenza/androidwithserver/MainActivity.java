package com.todo.trenza.androidwithserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button btn;
    String id = "";
    String email = "";
    TextView showdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button)findViewById(R.id.btngo);
        showdata = (TextView) findViewById(R.id.showdata);
        fetchingData();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchGo();
            }
        });
    }
    void fetchingData(){
        String url = "http://192.168.0.102/dev/magento/test.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0;i<response.length();i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        email = email + jsonObject.getString("email")+"\n\n";
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                showdata.setText(email);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log", error);
            }
        });

        com.todo.trenza.androidwithserver.AppController.getInstance().addToRequestQueue(jsonArrayRequest);
        Toast.makeText(getApplicationContext(),"Data Loaded Successfully!",Toast.LENGTH_SHORT).show();

    }
    void searchGo(){

        String url = "http://192.168.0.102/dev/magento/test.php";
        StringRequest sq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String > getParams(){
                Map<String, String> parr = new HashMap<String, String>();

                parr.put("status", btn.getText().toString());
                // n = parr.toString();

                return parr;

            }

        };

        AppController.getInstance().addToRequestQueue(sq);
        //Toast.makeText(getApplicationContext(),n,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this, MainActivity.class));
        Toast.makeText(getApplicationContext(),"Data Added Successfully!",Toast.LENGTH_SHORT).show();


    }
}
