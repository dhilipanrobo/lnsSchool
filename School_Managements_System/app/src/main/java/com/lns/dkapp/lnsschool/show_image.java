package com.lns.dkapp.lnsschool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class show_image extends AppCompatActivity {
    ListView listView;
    String[] image_path;
    private int json_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_show_image);
        log();
        listView=findViewById(R.id.listview);

    }

    void log () {

        //  Toast.makeText ( this, ""+STname+"_"+STpass, Toast.LENGTH_SHORT ).show ( );
        StringRequest request = new StringRequest(StringRequest.Method.POST, "https://welleservices.com/school/api/galleryImages", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 //Toast.makeText (show_image.this, "res: "+response, Toast.LENGTH_LONG ).show ( );

                try {
                    log_json(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(show_image.this, "error url", Toast.LENGTH_SHORT).show();


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
                params.put("gtitle_id",constont.titleid);


                return params;
            }



        };
        Volley.newRequestQueue(this).add(request);

    }


    private void log_json(String json) throws JSONException {

        JSONObject jsonObj = new JSONObject(json);
        JSONArray jsonArray = jsonObj.getJSONArray("galleryImages");


        image_path=new String[jsonArray.length()];

        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject obj = jsonArray.getJSONObject(i);
            image_path[i] = obj.getString("image_path");
        }
        json_val=jsonArray.length();


        AppointmentAdapter2 adapter2=new AppointmentAdapter2();
        listView.setAdapter(adapter2);

    }




    class AppointmentAdapter2 extends BaseAdapter


    {



        @Override
        public int getCount() {

            return json_val;
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
        public View getView(final int position,  View convertView, final ViewGroup parent) {

            convertView = null;

            if (convertView == null)
            {

                convertView = getLayoutInflater().inflate(R.layout.image_layout, null);
                ImageView img = convertView.findViewById(R.id.imageView8);
                Picasso.with ( show_image.this ).load ( image_path[position] ).into (img);
                //Toast.makeText (show_image.this, "res: "+image_path[position], Toast.LENGTH_LONG ).show ( );
            }

            return convertView;
        }





    }
}
