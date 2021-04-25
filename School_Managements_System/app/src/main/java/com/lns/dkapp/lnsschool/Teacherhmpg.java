package com.lns.dkapp.lnsschool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Teacherhmpg extends AppCompatActivity {
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_teacherlogin);
      //tv1=findViewById(R.id.st_name1);
       tv1.setText(constont.firstname1);
    }
    public void Attendance(View view)
    {
        Intent intent=new Intent(Teacherhmpg.this,TeacherAttendnce.class);
        startActivity(intent);
    }
    public void HomeWork(View view)
    {
        Intent intent=new Intent(Teacherhmpg.this,TeacherHmWrk.class);
        startActivity(intent);
    }
    public void Noticeboard(View view)
    {
        Intent intent=new Intent(Teacherhmpg.this,TeacherNtcBrd.class);
        startActivity(intent);
    }
}
