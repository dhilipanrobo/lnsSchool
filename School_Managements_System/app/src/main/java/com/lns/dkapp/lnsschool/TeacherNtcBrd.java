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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TeacherNtcBrd extends AppCompatActivity implements View.OnClickListener {
    TextView date;
    EditText edittext_title,editText_msg;
    Button sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ntc_brd);
        date=findViewById(R.id.textView23);
        edittext_title=findViewById(R.id.editText4);
        editText_msg = findViewById(R.id.editText3);
        editText_msg.getText().toString();
        sub = findViewById(R.id.button5);
        sub.setOnClickListener(this);

        Toast.makeText(this, ""+constont.school_id, Toast.LENGTH_SHORT).show();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String mdate = df.format(Calendar.getInstance().getTime());
        date.setText(mdate);
    }

    @Override
    public void onClick(View v) {
        if (editText_msg.length()==0)
        {
            Toast.makeText(this, "please enter notice", Toast.LENGTH_SHORT).show();
        }
        else {


            subject_url(edittext_title.getText().toString(),constont.school_id,date.getText().toString(),editText_msg.getText().toString());

        }


    }
    void subject_url(final String event_title, final String school_id, final String event_date, final String event_msg) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.getputnotice_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //
                 Toast.makeText(TeacherNtcBrd.this, "res" + response, Toast.LENGTH_SHORT).show();

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
                params.put("event_title", event_title);
                params.put("school_id", constont.school_id);
                params.put("event_date", event_date);
                params.put("event_message", event_msg);


                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }

}
