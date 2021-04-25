package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override

    protected void  onStart() {2
        super.onStart();

        Log.i("start","Start");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("resume","resume");
    }

    @Override
    protected  void  onStop() {
        super.onStop();
        Log.i("stop","Stop");
    }
}
