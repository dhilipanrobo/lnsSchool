package com.lns.dkapp.lnsschool;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Galpic extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    TextView titlle;
    String[]title,title_id,createdon,image_path;
    SliderLayout sliderLayout;
    ListView listView;
    HashMap<String, String> Hash_file_maps;
   // String request_url="http://106.51.98.57/schoolApp/upload/"+imagepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        listView=findViewById(R.id.listview);
        galpic(constont.gtitleid);



    }

    @Override
    protected void onStop() {



        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

        Toast.makeText(this, slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    void galpic(final String gtitle_id) {
        StringRequest request = new StringRequest(StringRequest.Method.POST, url.glryimg_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
             //   Toast.makeText(Galpic.this, "res" + response, Toast.LENGTH_SHORT).show();

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
            @Override
            public Map<String, String> getParams () throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("gtitle_id",constont.titleid);
                return params;
            }



        };

        Volley.newRequestQueue(this).

                add(request);

    }

    void loadInttoListView(String json) throws Exception {
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("galleryImages");


            title = new String[jsonArray.length()];
            title_id = new String[jsonArray.length()];
            createdon = new String[jsonArray.length()];
            image_path = new String[jsonArray.length()];


            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                title[i] = obj.getString("title");
                title_id[i] = obj.getString("title_id");
                createdon[i] = obj.getString("created_on");
                image_path[i] = obj.getString("image_path");

            }



            for (int i = 0; i < jsonArray.length(); i++) {
                constont.image_path=image_path[i].toString();
                constont.titleid=title_id[i].toString();

            }
        }
        catch (Exception e)
        {

        }

        AppointmentAdapter_cos adapter_cos = new AppointmentAdapter_cos();
        listView.setAdapter(adapter_cos);
    }


    class AppointmentAdapter_cos extends BaseAdapter {
        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return title_id.length;
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
                convertView = getLayoutInflater().inflate(R.layout.activity_galpic2, null);
                ImageView imageView=convertView.findViewById(R.id.imageView7);
                imageView.setImageURI(Uri.parse(image_path[position]));




            }


            Animation animation= AnimationUtils.loadAnimation(Galpic.this,R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }

    }
}

