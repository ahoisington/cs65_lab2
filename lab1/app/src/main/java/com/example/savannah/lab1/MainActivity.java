package com.example.savannah.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *   This activity holds the bulk of the UI for creating a user profile
 *   Based on the activity example in http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
