package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
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

public class TeacherHmWrk extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    ListView clasid;
    String[] clssid, clsname, subname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hm_wrk);
        clasid = findViewById(R.id.classid);
        clasid.setOnItemClickListener(this);


        classname();


    }

    @Override
    public void onClick(View v) {


    }


    void classname() {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.getclass_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //  Toast.makeText(TeacherHmWrk.this, "res" + response, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoqrcoad_cos_loy(response);
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
                params.put("school_id", constont.school_id);
                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException {
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("classes");

            clssid = new String[jsonArray.length()];
            clsname = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                clssid[i] = obj.getString("classid");
                clsname[i] = obj.getString("className");

            }

            AppointmentAdapter_cos adapter_cos = new AppointmentAdapter_cos();
            clasid.setAdapter(adapter_cos);


        } catch (Exception e) {


        }

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //subject_url(clssid[position].toString());

       Toast.makeText(this, "ee"+clssid[position], Toast.LENGTH_SHORT).show();
       constont.classid=clssid[position].toString();
       constont.classname=clsname[position].toString();
        Intent i=new Intent(getApplicationContext(),teasub.class);
        startActivity(i);
    }


    class AppointmentAdapter_cos extends BaseAdapter {


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
                convertView = getLayoutInflater().inflate(R.layout.teahmwrk_template, null);
                TextView textView = convertView.findViewById(R.id.textView39);
                textView.setText(clsname[position]);

            }


            Animation animation = AnimationUtils.loadAnimation(TeacherHmWrk.this, R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }


    }


}