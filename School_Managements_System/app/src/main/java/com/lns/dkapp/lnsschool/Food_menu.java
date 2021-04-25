package com.lns.dkapp.lnsschool;

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

public class Food_menu extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ListView listView;
    String[] lunch_itm,snks,created_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_food_menu);
        listView = findViewById(R.id.listview);
        listView.setOnItemClickListener(this);
      //  Toast.makeText(this, "res:"+constont.idapi, Toast.LENGTH_SHORT).show();
        foodmenu();

    }

    void foodmenu() {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.food_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
               // Toast.makeText(Food_menu.this, "res" + response, Toast.LENGTH_SHORT).show();

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

            //@Override
            //protected Map<String, String> getParams() throws AuthFailureError {
              //  Map<String, String> params = new HashMap<>();
                //params.put("className", clsname);


                //return params;
           // }
        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException {
        try {
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("food");


            lunch_itm = new String[jsonArray.length()];
            snks = new String[jsonArray.length()];
            created_date = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject obj = jsonArray.getJSONObject(i);
                lunch_itm[i] = obj.getString("lunch_items");
                snks[i] = obj.getString("snacks");
                created_date[i] = obj.getString("created_on");



            }


            Food_menu.AppointmentAdapter_cos adapter_cos = new Food_menu.AppointmentAdapter_cos();
            listView.setAdapter(adapter_cos);


        } catch (Exception e) {


        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "uuu", Toast.LENGTH_SHORT).show();
    }

    class AppointmentAdapter_cos extends BaseAdapter {
        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return lunch_itm.length;
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
                convertView = getLayoutInflater().inflate(R.layout.food_templet, null);

                TextView cat=convertView.findViewById(R.id.textView8);
                cat.setText(lunch_itm[position]);
                TextView catt=convertView.findViewById(R.id.textView7);
                catt.setText(snks[position]);
                TextView cat1=convertView.findViewById(R.id.textView9);
                cat1.setText(created_date[position]);
                String Date = created_date[position];
                String[] splitStr = Date.split("-");


            }

            Animation animation= AnimationUtils.loadAnimation(Food_menu.this,R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }

    }
}
