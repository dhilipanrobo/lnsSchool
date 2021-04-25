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

import java.util.HashMap;
import java.util.Map;

public class Attendance extends AppCompatActivity {
    TextView textView;
    ListView listView1;
    String[] mnth,wrkdys,prsnt,absnt,percntg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_attendance);
     listView1 =findViewById(R.id.listview1);

   attendance("1");
}
    void attendance(final String wrkingdays) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.attendance_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                 //Toast.makeText(Attendance.this, "res"+response, Toast.LENGTH_SHORT).show();
                try{
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
                params.put("WorkingDays",wrkingdays);
                return params;
            }

        };
        Volley.newRequestQueue(this).add(request);

    }

    private void loadIntoqrcoad_cos_loy(String json) throws JSONException
    {
        try{
            JSONObject jsonObj = new JSONObject(json);
            JSONArray jsonArray = jsonObj.getJSONArray("monthly_attendance");
            //Toast.makeText(this, "json"+jsonArray, Toast.LENGTH_SHORT).show();

            mnth=new String[jsonArray.length()];
            wrkdys=new String[jsonArray.length()];
            prsnt=new String[jsonArray.length()];
            absnt=new String[jsonArray.length()];
            percntg=new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++)
            {

                JSONObject obj = jsonArray.getJSONObject(i);
                mnth[i] = obj.getString("month");
                wrkdys[i] = obj.getString("WorkingDays");
                prsnt[i] = obj.getString("PresentDays");
                absnt[i] = obj.getString("AbsentDays");
                percntg[i] = obj.getString("Percentage");


            }



            Attendance.AppointmentAdapter_cos adapter_cos = new Attendance.AppointmentAdapter_cos();
            listView1.setAdapter(adapter_cos);




        }catch (Exception e){


        }

    }

class AppointmentAdapter_cos extends BaseAdapter {


    @Override
    public int getCount() {
        // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
        return prsnt.length;
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
            convertView = getLayoutInflater().inflate(R.layout.attendance_templet, null);
            TextView textView=convertView.findViewById(R.id.textView13);
            textView.setText(mnth[position]);
            TextView textView4=convertView.findViewById(R.id.textView14);
            textView4.setText(wrkdys[position]);
            TextView textView1=convertView.findViewById(R.id.textView15);
            textView1.setText(prsnt[position]);
            TextView textView2=convertView.findViewById(R.id.textView16);
            textView2.setText(absnt[position]);
            TextView textView3=convertView.findViewById(R.id.textView17);
            textView3.setText(percntg[position]+"%");

        }


        Animation animation = AnimationUtils.loadAnimation(Attendance.this, R.anim.listview_left);

        convertView.startAnimation(animation);


        return convertView;
    }


}

}

