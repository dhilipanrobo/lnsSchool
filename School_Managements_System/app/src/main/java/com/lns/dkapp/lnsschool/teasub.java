package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
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

public class teasub extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ListView subject;
    String[]clssid,subname,subid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_teasub);
        subject = findViewById(R.id.subject);
        subject.setOnItemClickListener(this);
        subject_url(constont.classid);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        constont.subjectname=subname[position].toString();
        constont.subjectid=subid[position].toString();

        Toast.makeText(this, "selected"+constont.subjectid, Toast.LENGTH_SHORT).show();
        Intent o=new Intent(getApplicationContext(),Teahomework.class);
        startActivity(o);

    }

    void subject_url(final String class_id) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.getsubj_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(teasub.this, "res" + response, Toast.LENGTH_SHORT).show();

                try {
                    subject(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
            subid = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                clssid[i] = obj.getString("classid");
                subname[i] = obj.getString("subjectName");
                subid[i] = obj.getString("subjectid");


            }

            try {

                AppointmentAdapter1 adapter1 = new AppointmentAdapter1();
                subject.setAdapter(adapter1);
            }catch (Exception e){}

        } catch (Exception e) {


        }

    }


    class AppointmentAdapter1 extends BaseAdapter {


        @Override
        public int getCount() {
            return clssid.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {

            convertView = null;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.teahmwrk_template2, null);
                TextView textView1 = convertView.findViewById(R.id.textView40);
                textView1.setText(subname[position]);

            }


            Animation animation = AnimationUtils.loadAnimation(teasub.this, R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }


    }
}
