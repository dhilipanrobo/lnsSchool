package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Teahomework extends AppCompatActivity implements View.OnClickListener {
    TextView text;
    EditText edit;
    Button sub;
    String[]clssid,subname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teahomework);

         text=findViewById(R.id.textView41);
         text.setText(constont.classname+"   "+constont.subjectname);
         edit = findViewById(R.id.editText);
         edit.getText().toString();
         sub = findViewById(R.id.button4);
         sub.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (edit.length()==0)
        {
            Toast.makeText(this, "please enter homework", Toast.LENGTH_SHORT).show();
        }
        else {
           // Toast.makeText(this, "homework inserted successfully", Toast.LENGTH_SHORT).show();

            subject_url(constont.classid,constont.school_id,constont.subjectid,edit.getText().toString());

        }


    }
    void subject_url(final String class_id, final String school_id, final String subject_id, final String homework) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.getput_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Teahomework.this, "" + response, Toast.LENGTH_SHORT).show();

                Intent h=new Intent(getApplicationContext(),Teacherhmpg.class);
                startActivity(h);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })

        {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", constont.idapi);
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("class_id", class_id);
                params.put("school_id", constont.school_id);
                params.put("subject_id", subject_id);
                params.put("homework", homework);


                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }

    private void subject(String json) throws JSONException {
        try {
            JSONObject jsonObj = new JSONObject(json);

            JSONArray jsonArray = jsonObj.getJSONArray("subjects");


            clssid = new String[jsonArray.length()];
            subname = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                clssid[i] = obj.getString("classid");
                subname[i] = obj.getString("subjectName");

            }
            for (int i = 0; i < jsonArray.length(); i++) {
         //   constont.homework=clssid[i].toString();
            }



        } catch (Exception e) {


        }

    }
}
