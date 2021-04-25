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

public class RankCard extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{
    TextView textView,textView1;
    ListView listView;
    String[] examid,exmname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rank_card);
        listView =findViewById(R.id.listview1);
        listView.setOnItemClickListener(this);

        textView=findViewById(R.id.textView18);
        textView1=findViewById(R.id.textView19);
        log("4");
    }
    void log (final String examid) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.examlist_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(RankCard.this, "res" + response, Toast.LENGTH_SHORT).show();
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


        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException
    {
        try{
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("examList");


            examid=new String[jsonArray.length()];
            exmname=new String[jsonArray.length()];
          //  valid_to_qr=new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                examid[i] = obj.getString("examList_id");
                exmname[i] = obj.getString("examName");

                
            }
          //  for (int i=0;i<jsonArray.length();i++)
          //  {
           //     constont.examid=examid.toString();
          //  }



            RankCard.AppointmentAdapter_cos adapter_cos = new RankCard.AppointmentAdapter_cos();
            listView.setAdapter(adapter_cos);
        }catch (Exception e){


        }

    }

    @Override
    public void onClick(View v) {

       }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "exam"+examid[position], Toast.LENGTH_SHORT).show();

        Intent i=new Intent(getApplicationContext(),ExamMark.class);
       startActivity(i);
       constont.examid=examid[position].toString();
    }

    class AppointmentAdapter_cos extends BaseAdapter {


        @Override
        public int getCount() {
            return examid.length;
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
                convertView = getLayoutInflater().inflate(R.layout.rankcard_templet, null);
                TextView textView=convertView.findViewById(R.id.textView18);
                textView.setText(examid[position]);
                TextView textview1=convertView.findViewById(R.id.textView19);
                textview1.setText(exmname[position]);

            }


            Animation animation = AnimationUtils.loadAnimation(RankCard.this, R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }


    }

}

