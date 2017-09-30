package com.example.savannah.lab1;

import android.content.SharedPreferences;
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
   // public static String INTERNAL_FILE = "internal-file"; // where we're storing picture
    public static String SHARED_PREF = "my_sharedpref"; // where we are storing profile text
    public String mInstance = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // sets toolbar as the app bar for the activity

        // initialize text fields so if we want to, we can clear them later
        handle = (EditText) findViewById(R.id.username_edittext);
        fullName = (EditText) findViewById(R.id.full_name_edittext);
        password = (EditText) findViewById(R.id.password_edittext);

        //Restore preferences
        SharedPreferences sp = getSharedPreferences(SHARED_PREF, 0); // get settings
        handle.setText(sp.getString("handle","")); // get handle from sp and display it
        fullName.setText(sp.getString("fullName","")); // get handle from sp and display it
        password.setText(sp.getString("password","")); // get handle from sp and display it

        // save instance state if not null
        if (savedInstanceState != null){
            mInstance = savedInstanceState.getString("state");
        }

        Log.d("TAG", ""+mInstance);

    }

    // invoked when the activity may be temporarily destroyed, save the instance state here
    @Override
    public void onSaveInstanceState(Bundle outState) {
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
        //outState.putString("state", mEditText.getText().toString());
    }


    // save button was clicked. validate fields and save entered information
    public void onSaveClicked(View v){

        // validate fields. check that handle, name, and password are not empty

        onSaveClickedSP(v); // save text to shared preferences

        // save photo to internal storage
    }

    public void onSaveClickedSP(View v) {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF, 0);
        SharedPreferences.Editor editor = sp.edit(); // get editor

        // save the text into shared prefs
        editor.putString("handle", handle.getText().toString());
        editor.putString("fullName", fullName.getText().toString());
        editor.putString("password", password.getText().toString());

        editor.commit(); // commit the changes to shared prefs

    }


    /*
    // saving picture to internal storage, in a file
    public void onSaveClickedIS(View v) {

        FileOutputStream fos = null;
        try {
            fos = openFileOutput(INTERNAL_FILE, Context.MODE_PRIVATE);
            fos.write(handle.getText().toString().getBytes());
            //fos.write(mEditText.getText().toString().getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // load picture from internal storage
    public void loadSavedData() {

        FileInputStream fis = null;
        try {
            fis = openFileInput(INTERNAL_FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null ){
                sb.append(line);
            }
            handle.setText(sb.toString());
            //mEditText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void onLoadClickedIS(View v) {

        FileInputStream fis = null;
        try {
            fis = openFileInput(INTERNAL_FILE);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null ){
                sb.append(line);
            }
            handle.setText(sb.toString());
            //mEditText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    */


    /* clears all entered data to default when clear button is pressed */
    public void onClearText(View v) {
        handle.setText("");
        fullName.setText("");
        password.setText("");

        // clear shared preferences: fields are blank, store blank values into shared prefs
        onSaveClickedSP(v);
    }

    // Lifecycle instrumentation
    @Override protected void onStart(){
        Log.d("CYCLE", "onStart");
        super.onStart();
    }


    @Override protected void onResume(){
        Log.d("CYCLE", "onResume");
        super.onResume();
        //loadSavedData();
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
