package com.lns.dkapp.lnsschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class ExamMark extends AppCompatActivity implements View.OnClickListener{
    ListView lv;
    String[] sbjname,mrk,ttl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_exam_mark);
        lv=findViewById(R.id.listview2);
     // Toast.makeText(this, "res:"+constont.idapi, Toast.LENGTH_SHORT).show();
        exammark();
    }

    @Override
    public void onClick(View v) {

    }
    void exammark () {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.exammrk_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ExamMark.this, "res" +response, Toast.LENGTH_SHORT).show();
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
                params.put("examList_id","4");
                return params;
            }


        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException
    {
        try{
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("examReport");


            sbjname=new String[jsonArray.length()];
            mrk=new String[jsonArray.length()];
            ttl=new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                sbjname[i] = obj.getString("subjectName");
                mrk[i] = obj.getString("mark");
                ttl[i] = obj.getString("total");


            }
           // ExamMark.AppointmentAdapter_cos adapter_cos = new ExamMark.AppointmentAdapter_cos();
            //lv.setAdapter(adapter_cos);
        }catch (Exception e) {


        }
            ExamMark.AppointmentAdapter_cos adapter_cos = new ExamMark.AppointmentAdapter_cos();
            lv.setAdapter(adapter_cos);

    }
    class AppointmentAdapter_cos extends BaseAdapter {
        @Override
        public int getCount() {
            return sbjname.length;
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
                convertView = getLayoutInflater().inflate(R.layout.exam_template, null);

                TextView cat=convertView.findViewById(R.id.textView25);
                cat.setText(sbjname[position]);
                TextView catt=convertView.findViewById(R.id.textView26);
                catt.setText(mrk[position]);
                TextView cat1=convertView.findViewById(R.id.textView38);
                cat1.setText(ttl[position]);




            }

            Animation animation= AnimationUtils.loadAnimation(ExamMark.this,R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }

    }
}

