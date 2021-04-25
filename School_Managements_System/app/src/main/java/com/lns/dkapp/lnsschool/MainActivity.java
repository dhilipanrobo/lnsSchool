package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView tv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        img=findViewById(R.id.profile_image);


        tv1 = findViewById(R.id.st_name);
        tv1.setText(constont.firstname_st);


        Picasso.with ( MainActivity.this ).load ( constont.student_img ).into ( img);

    }

    public  void Noticeboard (View view){

        Intent v=new Intent(this,Noticeboard.class);
        startActivity(v);

    }

    public  void food_menu (View view){

        Intent v=new Intent(this,Food_menu.class);
        startActivity(v);

    }
    public void Gallery (View view)
    {
        Intent v= new Intent(this,Gallery.class);
        startActivity(v);
    }
    public void Attendance(View view)
    {
        Intent v= new Intent(this,Attendance.class);
        startActivity(v);
    }
    public void RankCard(View view)
    {
        Intent v= new Intent(this,RankCard.class);
        startActivity(v);
    }
    public void HomeWork(View view)
    {
        Intent v= new Intent(this,Home_work.class);
        startActivity(v);
    }


}


