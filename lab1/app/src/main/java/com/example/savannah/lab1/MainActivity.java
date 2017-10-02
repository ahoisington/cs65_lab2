/**
 *   This activity holds the bulk of the UI for creating a user profile.
 *   Stores profile info with internal storage
 *   Clearing fields with Clear button
 *
 *   Based on Android guides and the following examples:
 *   activity example: http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 *   internal storage: http://www.cs.dartmouth.edu/~sergey/cs65/examples/StorageOptions/
 *   camera: http://www.cs.dartmouth.edu/~sergey/cs65/examples/SimpleCam/
 *   permissions: http://www.cs.dartmouth.edu/~sergey/cs65/examples/KittyDownload/
 */

package com.example.savannah.lab1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;




public class MainActivity extends AppCompatActivity {

    // for photo
    private Bitmap photoBitmap;
    private ImageView photoImageView;

    // for text entered
    private EditText handle;
    private EditText fullName;
    private EditText password;

    // for storage
    // public static String INTERNAL_FILE = "internal-file"; // where we're storing picture
    public static String SHARED_PREF = "my_sharedpref"; // where we are storing profile text
    public String mInstance = "";

    //  request code for permissions
    private static final int REQUEST_ALL_PERMISSIONS = 1; // request code for all permissions together
    //private static final int REQUEST_EXTERNAL_STORAGE = 0;
    private static final int REQUEST_IMAGE_CAPTURE = 1; // used to take pic, when cam button clicked

    // for activity dialog box
    private int ACTIVITY_REQ_CODE = 1001;




    /********************** Activity Lifecycle ***************************/
    // get camera permissions, initialize, and restore shared preferences
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize text fields so if we want to, we can clear them later
        handle = (EditText) findViewById(R.id.username_edittext);
        fullName = (EditText) findViewById(R.id.full_name_edittext);
        password = (EditText) findViewById(R.id.password_edittext);

        // save instance state if not null
        if (savedInstanceState != null){
            mInstance = savedInstanceState.getString("state");
        }
        
        final Button accountBtn = (Button) findViewById(R.id.account_button);
        accountBtn.setEnabled(true);
        final Button clearBtn = (Button) findViewById(R.id.clear_button);


        // disabling "i have an account" button, enabling clear button upon text entry
        handle.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                accountBtn.setEnabled(false);
                accountBtn.setClickable(false);
                accountBtn.setVisibility(View.INVISIBLE);
                clearBtn.setEnabled(true);
                clearBtn.setClickable(true);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            @Override
             public void afterTextChanged(Editable string) {}
        });
        fullName.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                accountBtn.setEnabled(false);
                accountBtn.setClickable(false);
                accountBtn.setVisibility(View.INVISIBLE);
                clearBtn.setEnabled(true);
                clearBtn.setClickable(true);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            @Override
            public void afterTextChanged(Editable string) {}
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                accountBtn.setEnabled(false);
                accountBtn.setClickable(false);
                accountBtn.setVisibility(View.INVISIBLE);
                clearBtn.setEnabled(true);
                clearBtn.setClickable(true);
            }
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {}
            @Override
            public void afterTextChanged(Editable string) {}
        });
        
        //dialog box pops up
        /*
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    showDialog(PASSWORD_AUTH_DIALOG_ID);
                }
            }
        });
        */

        Log.d("TAG", ""+mInstance);

    }

    // initialize views
    @Override protected void onStart(){
        Log.d("CYCLE", "onStart");
        super.onStart();

    }

    // check permissions
    @Override
    protected void onResume(){
        Log.d("CYCLE", "onResume");
        super.onResume();

        // check permissions
        checkPermissions();

        // initialize toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); // sets toolbar as the app bar for the activity
/*
        // initialize text fields so if we want to, we can clear them later
        handle = (EditText) findViewById(R.id.username_edittext);
        fullName = (EditText) findViewById(R.id.full_name_edittext);
        password = (EditText) findViewById(R.id.password_edittext);

*/
        // initialize photo stuff so we can clear it later
        photoImageView = findViewById(R.id.photo);
        if( photoBitmap != null)
            photoImageView.setImageBitmap(photoBitmap);


        //Restore preferences
        SharedPreferences sp = getSharedPreferences(SHARED_PREF, 0); // get settings
        handle.setText(sp.getString("handle","")); // get handle from sp and display it
        fullName.setText(sp.getString("fullName","")); // get handle from sp and display it
        password.setText(sp.getString("password","")); // get handle from sp and display it

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


    /************ Permissions: Camera & External Storage **********/

    // called in onStart. check if we have permissions for external storage and camera
    protected void checkPermissions(){

        // put permissions in an array to pass to ActivityCompat together
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        // For each permission, check if granted
        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // we don't have permission. ask for permission and show permissions dialog
                Log.d("PERM", "requesting permission");

                ActivityCompat.requestPermissions( this, permissions, REQUEST_ALL_PERMISSIONS);
            }
        }

    }


    // Shows toast saying whether permission was granted or not
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d( "PERM", "in onRequestPermissionsResult");
        switch (requestCode) {
            case REQUEST_ALL_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                 } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    /********* keeps phone flipping from messing up ImageView **********/


    // Comment out the following two functions and see what happens
    //   with the ImageView after you flip the phone's orientation.
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // save the bitmap
        Log.d("STATE", "onSaveState");
        outState.putParcelable("IMG", photoBitmap);
        super.onSaveInstanceState(outState);
    }

    // see above
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("STATE", "onRestoreState");
        if (savedInstanceState != null){
            photoBitmap = savedInstanceState.getParcelable("IMG");
            if(photoBitmap != null && photoImageView != null) {
                photoImageView.setImageBitmap(photoBitmap);
            }
        }
    }


    /******************** Taking photo ********************/
    // takes photo when camera button clicked
    // The Android Camera application encodes the photo in the return Intent delivered to
    // onActivityResult() as a small Bitmap in the extras, under the key "data".
    public void camButtonClicked(View v){
        // Call intent to delegate taking a pic
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        /* resolveActivity() returns first activity component that can handle the intent
        * prevents app from crashing when nothing can handle the intent in startActivityForResult()
        */
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Call to start external activity & handling image data when focus returns to main activity
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    /****** Handling photo and dialog activities ******/
    // Get the image back from the camera, displays photo (a Bitmap) in ImageView.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photoBitmap = (Bitmap) extras.get("data");
            photoImageView.setImageBitmap(photoBitmap);
        }

        if (requestCode == ACTIVITY_REQ_CODE) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("USERNAME");
                String pass = data.getStringExtra("PASSWORD");

                Toast.makeText(this, "Got " + name + "/" + pass,
                        Toast.LENGTH_LONG).show();

            }
        }
    }

    /******** Activity dialog  ********/
    public void showAuthActivity(View view) {
        Intent i = new Intent(this, AuthActivity.class);
        startActivityForResult(i, ACTIVITY_REQ_CODE);
    }


    /***************** Saving Profile *********************/
    // save button was clicked. validate fields and save entered information
    protected void onSaveClicked(View v){

        // validate fields. check that handle, name, and password are not empty

        onSaveClickedSP(v); // save text to shared preferences

        // save photo to external storage
    }

    // save the entered text into shared preferences
    protected void onSaveClickedSP(View v) {
        SharedPreferences sp = getSharedPreferences(SHARED_PREF, 0);
        SharedPreferences.Editor editor = sp.edit(); // get editor

        // save the text into shared prefs
        editor.putString("handle", handle.getText().toString());
        editor.putString("fullName", fullName.getText().toString());
        editor.putString("password", password.getText().toString());

        editor.commit(); // commit the changes to shared prefs

    }


    /******************** Clearing Profile ********************/
    /* clears all entered data to default when clear button is pressed */
    protected void onClearText(View v) {
        handle.setText("");
        fullName.setText("");
        password.setText("");

        // clear shared preferences: fields are blank, store blank values into shared prefs
        onSaveClickedSP(v);
    }


}
