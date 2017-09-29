package com.example.savannah.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

/**
 *   This activity holds the bulk of the UI for creating a user profile
 *   Based on the activity example in http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // sets toolbar as the app bar for the activity

    }

    // Lifecycle instrumentation
    @Override protected void onStart(){
        Log.d("CYCLE", "onStart");
        super.onStart();
    }

    @Override protected void onResume(){
        Log.d("CYCLE", "onResume");
        super.onResume();
    }

    @Override protected void onPause(){
        Log.d("CYCLE", "onPause");
        super.onPause();
    }

    @Override protected void onStop(){
        Log.d("CYCLE", "onStop");
        super.onStop();
    }

    @Override protected void onDestroy(){
        Log.d("CYCLE", "onDestroy");
        super.onDestroy();
    }
}
