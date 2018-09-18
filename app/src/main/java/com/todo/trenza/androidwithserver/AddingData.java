package com.todo.trenza.androidwithserver;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddingData extends AppCompatActivity {
    EditText name;
    Button submit;
    String n = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_data);

        name = (EditText) findViewById(R.id.name);
        submit = (Button) findViewById(R.id.btnSubmit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://192.168.0.12/api/adddata.php";


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

                        parr.put("name", name.getText().toString());
                       // n = parr.toString();

                        return parr;

                    }

                };

                AppController.getInstance().addToRequestQueue(sq);

                //Toast.makeText(getApplicationContext(),n,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Data Added Successfully!",Toast.LENGTH_SHORT).show();


            }
        });
    }
}
