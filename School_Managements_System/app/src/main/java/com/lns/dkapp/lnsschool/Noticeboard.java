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

public class Noticeboard extends AppCompatActivity {
ListView listView;
    String[]evtdt,evttitle,msg,poston;
    java.util.Date oneWayTextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
       // requestWindowFeature(Window.FEATURE_NO_TITLE);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_noticeboard);
        listView =findViewById(R.id.listview);
        log("sports");

    }

    void log (final String evnt) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.notice_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(Noticeboard.this, "res"+response, Toast.LENGTH_SHORT).show();
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
                params.put("event_title",evnt);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException
    {
        try{
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("noticeBoard");
            //Toast.makeText(this, "json"+jsonArray, Toast.LENGTH_SHORT).show();

            evtdt=new String[jsonArray.length()];
            evttitle=new String[jsonArray.length()];
            msg=new String[jsonArray.length()];
            poston=new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                evtdt[i] = obj.getString("event_date");
                evttitle[i] = obj.getString("event_title");
                msg[i] = obj.getString("message");
                poston[i] = obj.getString("posted_on");


            }



            AppointmentAdapter_cos adapter_cos = new AppointmentAdapter_cos();
            listView.setAdapter(adapter_cos);




        }catch (Exception e){


        }

    }

    class AppointmentAdapter_cos extends BaseAdapter {



        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return evtdt.length;
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
                convertView = getLayoutInflater().inflate(R.layout.noticeboard_template, null);
                TextView textView_heading=convertView.findViewById(R.id.textView_heading);
                textView_heading.setText(evttitle[position]);
                TextView textView_news=convertView.findViewById(R.id.textView_news);
                textView_news.setText(msg[position]);
                TextView textView_new=convertView.findViewById(R.id.textView_new);
                textView_new.setText("posted on :"+" "+poston[position]);



                String date =evtdt[position];
                SimpleDateFormat input = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat output = new SimpleDateFormat("dd");
                try {
                    oneWayTextview = input.parse(date);
                    TextView textView2=convertView.findViewById(R.id.textView_date);
                    textView2.setText(output.format(oneWayTextview));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                String date1 =evtdt[position];
                SimpleDateFormat input1 = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat output1 = new SimpleDateFormat("yyyy-mm");
                try {
                    oneWayTextview = input1.parse(date1);
                    TextView textView2=convertView.findViewById(R.id.textView_year);
                    textView2.setText(output1.format(oneWayTextview));
                } catch (ParseException e) {
                    e.printStackTrace();
                }



            }
            Animation animation= AnimationUtils.loadAnimation(Noticeboard.this,R.anim.listview_left);

            convertView.startAnimation(animation);
            return convertView;
        }





    }

}
