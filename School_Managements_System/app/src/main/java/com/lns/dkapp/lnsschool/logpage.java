package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class logpage extends AppCompatActivity {
    String[] skl_id, clss_id, std_id, api_key, role_id, first_name,lst_name,img_path,first_name_tec;
    CircularProgressButton loadingMe;
    String a = "0";
    private VideoView videoBG;
    MediaPlayer mMediaPlayer;
    EditText editText_name, editText_pass;
    int mCurrentVideoPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logpage);
        editText_name = findViewById(R.id.etEmailAddress);
        editText_pass = findViewById(R.id.etPassword);
        loadingMe = (CircularProgressButton) findViewById(R.id.loadingMe);



        loadingMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTask<String, String, String> demoLogin = new AsyncTask<String, String, String>() {
                    @Override
                    protected String doInBackground(String... params) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "done";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if (s.equals("done")) {
                            Intent i = new Intent(logpage.this, MainActivity.class);

                            loadingMe.setBackgroundResource(R.drawable.button2);

                            //  startActivity(i);
                            //  finish();
                        }
                    }
                };

                loadingMe.startAnimation();

                // demoLogin.execute();


                if (editText_name.length() == 0) {
                    Toast.makeText(logpage.this, "username is incorrect", Toast.LENGTH_SHORT).show();

                } else {
                    if (editText_pass.length() == 0) {
                        Toast.makeText(logpage.this, "password is incorrect", Toast.LENGTH_SHORT).show();
                    } else {
                        demoLogin.execute();
                        log(editText_name.getText().toString(), editText_pass.getText().toString());
                        //  Toast.makeText(logpage.this, "successfully login", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        ImageView imageView = findViewById(R.id.imageView);
        videoBG = (VideoView) findViewById(R.id.videoView);
        editText_name = findViewById(R.id.etEmailAddress);
        editText_pass = findViewById(R.id.etPassword);


        Uri uri = Uri.parse("android.resource://"
                + getPackageName()
                + "/"
                + R.raw.school);
        videoBG.setVideoURI(uri);
        videoBG.start();
        videoBG.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                mediaPlayer.setVolume(0, 0);

                mMediaPlayer.setLooping(true);

                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.mytra);
        imageView.startAnimation(animation);
        editText_pass.startAnimation(animation);
        editText_name.startAnimation(animation);

        Thread timer = new Thread() {

            public void run() {

                try {
                    sleep(5000);
                } catch (Exception e) {
                } finally {

                    // startActivity(v);
                    // finish();
                }
            }
        };
        timer.start();


    }

    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
    //    mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
     //   videoBG.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the video when resuming the Activity
        videoBG.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
//        mMediaPlayer.release();

     //   mMediaPlayer = null;
    }

    public void ok(View view) {

        Intent intent = new Intent(logpage.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void log(final String username, final String password) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.oldLoyalCustDetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


              // Toast.makeText (logpage.this, "res: "+response, Toast.LENGTH_LONG ).show ( );
                try {
                    loadIntoListView("[" + response + "]");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText (logpage.this, "Error ", Toast.LENGTH_LONG ).show ( );

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", username);
                params.put("password",password);
                params.put("school_id",constont.school_id);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void loadIntoListView(String json) throws Exception {
        JSONArray jsonArray = new JSONArray(json);

        role_id = new String[jsonArray.length()];
        first_name = new String[jsonArray.length()];
        first_name_tec = new String[jsonArray.length()];
        skl_id = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        img_path = new String[jsonArray.length()];
        std_id = new String[jsonArray.length()];
        clss_id = new String[jsonArray.length()];
        first_name = new String[jsonArray.length()];
        lst_name = new String[jsonArray.length()];


        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject Object = jsonArray.getJSONObject(i);

                role_id[i] = Object.getString("role_id");
                first_name[i] = Object.getString("first_name");
                first_name_tec[i] = Object.getString("first_name");
                skl_id[i] = Object.getString("school_id");
                api_key[i] = Object.getString("api_key");
                img_path[i] = Object.getString("imgPath");
                std_id[i] = Object.getString("student_id");
                clss_id[i] = Object.getString("class_id");
                first_name[i] = Object.getString("first_name");
                lst_name[i] = Object.getString("last_name");



            }
            for (int i = 0; i < jsonArray.length(); i++) {

                constont.role_id = role_id[i].toString();
                constont.firstname_st = first_name[i].toString();
                constont.firstname1 = first_name_tec[i].toString();
                constont.school_id = skl_id[i].toString();
                constont.idapi = api_key[i].toString();
                constont.student_img = img_path[i].toString();



            }

            Intent h=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(h);finish();

          //  if (constont.role_id.equals("4")) {
             //   log2(editText_name.getText().toString(), editText_pass.getText().toString());
             //   Intent o = new Intent(getApplicationContext(), MainActivity.class);
              //  startActivity(o);
               // finish();

           // } else {
                //log3(editText_name.getText().toString(), editText_pass.getText().toString());
                 //   Intent o = new Intent(getApplicationContext(), Teacherhmpg.class);
                  //  startActivity(o);
                  //  finish();
           // }
        } catch (Exception e) {
            try {
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject Object = jsonArray.getJSONObject(i);
                    skl_id[i] = Object.getString("school_id");
                    role_id[i] = Object.getString("role_id");
                    api_key[i] = Object.getString("api_key");
                    first_name_tec[i] = Object.getString("first_name");
                }
                for (int i = 0; i < jsonArray.length(); i++) {
                    //Toast.makeText(this, "test " + error[i], Toast.LENGTH_SHORT).show();

                    constont.idapi = api_key[i].toString();
                    constont.firstname1=first_name_tec[i].toString();
                    constont.role_id = role_id[i].toString();
                    constont.school_id=skl_id[i].toString();

                }

                Intent o = new Intent(getApplicationContext(), Teacherhmpg.class);
                  startActivity(o);
                  finish();


            } catch (Exception g) {
                // Toast.makeText(this, "username password incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
    void log2(final String username, final String password) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.oldLoyalCustDetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
              //  Toast.makeText(logpage.this, "res"+response, Toast.LENGTH_SHORT).show();
                try {
                    loadIntoListView1("[" + response + "]");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", username);
                params.put("password", password);
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void loadIntoListView1(String json) throws Exception {
        JSONArray jsonArray = new JSONArray(json);
        skl_id = new String[jsonArray.length()];
        std_id = new String[jsonArray.length()];
        clss_id = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        first_name = new String[jsonArray.length()];
        lst_name = new String[jsonArray.length()];




        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject Object = jsonArray.getJSONObject(i);
                skl_id[i] = Object.getString("school_id");
                std_id[i] = Object.getString("student_id");
                clss_id[i] = Object.getString("class_id");
                api_key[i] = Object.getString("api_key");
                first_name[i] = Object.getString("first_name");
                lst_name[i] = Object.getString("last_name");


            }
            for (int i = 0; i < jsonArray.length(); i++) {


                constont.idapi = api_key[i].toString();
                constont.firstname_st=first_name[i].toString();
                constont.lastname=lst_name[i].toString();
                constont.school_id=skl_id[i].toString();

            }




        } catch (Exception e) {
            //Toast.makeText(this, "username password incorrect", Toast.LENGTH_SHORT).show();
        }
        logpage.AppointmentAdapter_cos adapter_cos = new logpage.AppointmentAdapter_cos();
    }
    class AppointmentAdapter_cos extends BaseAdapter {


        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return first_name.length;
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
                convertView = getLayoutInflater().inflate(R.layout.activity_main, null);
                TextView textView=convertView.findViewById(R.id.st_name);
                textView.setText(first_name[position]);


            }


            Animation animation = AnimationUtils.loadAnimation(logpage.this, R.anim.listview_left);

            convertView.startAnimation(animation);


            return convertView;
        }


    }

    void log3(final String username, final String password) {

        StringRequest request = new StringRequest(StringRequest.Method.POST, url.oldLoyalCustDetails_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


                Toast.makeText (logpage.this, "res: "+response, Toast.LENGTH_LONG ).show ( );
                try {
                    loadIntoListView2("[" + response + "]");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", "1010");
                params.put("password", "2019-02-29");
                return params;
            }
        };
        Volley.newRequestQueue(this).add(request);
    }

    private void loadIntoListView2(String json) throws Exception {
        JSONArray jsonArray = new JSONArray(json);
        skl_id = new String[jsonArray.length()];
        api_key = new String[jsonArray.length()];
        role_id = new String[jsonArray.length()];
        first_name_tec = new String[jsonArray.length()];



        logpage.AppointmentAdapter adapter_cos = new logpage.AppointmentAdapter();

    }

    class AppointmentAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            // Toast.makeText(loyalty_home.this, "count"+count_at3, Toast.LENGTH_SHORT).show();
            return first_name_tec.length;
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
                convertView = getLayoutInflater().inflate(R.layout.activity_teacherlogin, null);
              //  TextView textView=convertView.findViewById(R.id.st_name1);
                //textView.setText(first_name_tec[position]);
            }
            Animation animation = AnimationUtils.loadAnimation(logpage.this, R.anim.listview_left);
            convertView.startAnimation(animation);
            return convertView;
        }
    }
}

