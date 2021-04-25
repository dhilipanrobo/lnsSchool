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

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Home_work extends AppCompatActivity {
    ListView lv;
    String[] sbj,hmwrk,date;
    java.util.Date oneWayTextview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home_work);
        lv=findViewById(R.id.listview3);
        homework();

    }
    void homework() {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.hmwrk_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Home_work.this, "res" + response, Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               // params.put("school_id",constont.school_id);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException {
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("homework");


            sbj = new String[jsonArray.length()];
            hmwrk = new String[jsonArray.length()];
            date= new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                sbj[i] = obj.getString("subjectName");
                hmwrk[i] = obj.getString("homework");
                date[i] = obj.getString("hwDate");



            }


            Home_work.AppointmentAdapter_cos adapter_cos = new Home_work.AppointmentAdapter_cos();
            lv.setAdapter(adapter_cos);


        } catch (Exception e) {


        }

    }


    class AppointmentAdapter_cos extends BaseAdapter {
        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return sbj.length;
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
                convertView = getLayoutInflater().inflate(R.layout.homework_templet, null);

                TextView cat=convertView.findViewById(R.id.textView_heading);
                cat.setText(sbj[position]);
                TextView catt=convertView.findViewById(R.id.textView_news);
                catt.setText(hmwrk[position]);

                String date1 =date[position];
                SimpleDateFormat input = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat output = new SimpleDateFormat("dd");
                try {
                    oneWayTextview = input.parse(date1);
                    TextView textView2=convertView.findViewById(R.id.textView_date);
                    textView2.setText(output.format(oneWayTextview));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                String date2 =date[position];
                SimpleDateFormat input1 = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat output1 = new SimpleDateFormat("yyyy-mm");
                try {
                    oneWayTextview = input1.parse(date2);
                    TextView textView2=convertView.findViewById(R.id.textView_year);
                    textView2.setText(output1.format(oneWayTextview));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }

            Animation animation= AnimationUtils.loadAnimation(Home_work.this,R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }

    }
}


