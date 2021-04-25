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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class Gallery extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {

    TextView tv1, tv2, tv3;
    ImageView imageView2;
    ListView lv;
    String datetime;
    String[] title, created,title_id;
    java.util.Date oneWayTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_gallery);
        lv = findViewById(R.id.listview);
        lv.setOnItemClickListener(this);
        tv1 = findViewById(R.id.textView10);
        tv2 = findViewById(R.id.textView11);
        tv3 = findViewById(R.id.textView12);
        gallery();

        }
    public  void Galpic (View view){

        Intent v=new Intent(this,show_image.class);
        startActivity(v);

    }
    void gallery() {
        StringRequest request = new StringRequest(StringRequest.Method.POST, url.gallery_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(Gallery.this, "res" + response, Toast.LENGTH_SHORT).show();

                try {
                    loadInttoListView(response);
                } catch (Exception e) {
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
                public Map<String, String> getHeaders () throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", constont.idapi);
                return headers;
            }


        };

        Volley.newRequestQueue(this).

                add(request);

    }

        void loadInttoListView(String json) throws Exception {
            try {
                JSONObject jsonObj = new JSONObject(json);
                JSONArray jsonArray = jsonObj.getJSONArray("galleryTitles");


                title = new String[jsonArray.length()];
                created = new String[jsonArray.length()];
                title_id = new String[jsonArray.length()];

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject obj = jsonArray.getJSONObject(i);
                    title[i] = obj.getString("title");
                    created[i] = obj.getString("created_on");
                   title_id[i] = obj.getString("title_id");


                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    constont.gtitleid=title_id[i].toString();
                }
            }
            catch (Exception e)
            {

            }

        Gallery.AppointmentAdapter_cos adapter_cos = new Gallery.AppointmentAdapter_cos();
        lv.setAdapter(adapter_cos);
    }





    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

      //  ItemClicked item = adapter.getItemAtPosition(position);
        constont.titleid=title_id[position].toString();

        Intent v=new Intent(this,show_image.class);
        startActivity(v);

    }


    @Override
    public void onClick(View v) {

        Intent vv=new Intent(this,show_image.class);
        startActivity(vv);
    }


    class AppointmentAdapter_cos extends BaseAdapter {
        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return title.length;
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
                convertView = getLayoutInflater().inflate(R.layout.gallery_templet, null);
                TextView textView=convertView.findViewById(R.id.textView10);
                textView.setText(title[position]);

                String date =created[position];
                SimpleDateFormat input = new SimpleDateFormat("yyyy-mm-dd");
                SimpleDateFormat output = new SimpleDateFormat("dd-mm-yyyy");
                try {
                    oneWayTextview = input.parse(date);
                    TextView textView2=convertView.findViewById(R.id.textView12);
                    textView2.setText(output.format(oneWayTextview));
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }


            Animation animation= AnimationUtils.loadAnimation(Gallery.this,R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }

    }
}
