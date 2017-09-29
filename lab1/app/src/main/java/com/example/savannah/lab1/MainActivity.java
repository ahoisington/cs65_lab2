package com.example.savannah.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *   This activity holds the bulk of the UI for creating a user profile.
 *   Stores profile info with internal storage
 *   Clearing fields with Clear button
 *
 *   Based on the following examples:
 *   activity example: http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 *   internal storage: http://www.cs.dartmouth.edu/~sergey/cs65/examples/StorageOptions/
 */

public class MainActivity extends AppCompatActivity {


    public EditText handle;
    public EditText fullName;
    public EditText password;
    public static String INTERNAL_FILE = "internal-file"; // where we're storing profile info
    public String mInstance = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // sets toolbar as the app bar for the activity

        // get saved text fields
        handle = (EditText) findViewById(R.id.username_edittext);
        fullName = (EditText) findViewById(R.id.full_name_edittext);
        password = (EditText) findViewById(R.id.password_edittext);

        // save instance state if not null
        if (savedInstanceState != null){
            mInstance = savedInstanceState.getString("state");
        }

        Log.d("TAG", ""+mInstance);

    }


    /* clears fields when clear button is pressed */
    public void onClearText(View v) {
        handle.setText("");
        fullName.setText("");
        password.setText("");

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
