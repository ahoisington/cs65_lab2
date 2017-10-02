package com.example.savannah.lab1;

/**
 * This activity handles the pop up for password confirmation
 * Based on the activity example in http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 */

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 *   This activity should be
 */

public class AuthActivity extends Activity {

    private String firstPassword;

    @Override
    protected void onCreate(Bundle savedState){
        super.onCreate(savedState);

        setContentView(R.layout.activity_auth);

        firstPassword = getIntent().getStringExtra("firstPassword");

        final Button b = findViewById(R.id.button_done);
        //b.setAlpha(.3f);
        //b.setClickable(false);
        b.setEnabled(false);

        final EditText tp = findViewById(R.id.passwd);
        final ImageView checkmark = findViewById(R.id.green_check);;

         // enabling done button
        tp.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Log.d("TEXT", "afterTextChanged");
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("TEXT", "beforeTextChanged: '" + s.toString() + "'");
                checkmark.setVisibility(View.INVISIBLE); // no checkmark yet

            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                b.setAlpha(1f);
//                b.setClickable(true);

                checkmark.setVisibility(View.INVISIBLE); // no checkmark yet
                // check if password is correct
                if(confirmPassword(firstPassword, tp.getText().toString()) == true) {

                    // display the green checkmark
                    checkmark.setVisibility(View.VISIBLE);
                    checkmark.setImageResource(R.drawable.check);
                    b.setEnabled(true);
                }
            }
        });

        //  focus change
        tp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    Log.d("PASSBOX", "lost focus");
                }
                else {
                    Log.d("PASSBOX", "got focus");
                }
            }
        });


        // catch Enter typed in this box
        tp.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press

                        Toast.makeText(AuthActivity.this,
                                tp.getText(),
                                Toast.LENGTH_SHORT).show();
                        return true;
                    //}

                }
                return false;
            }
        });

    }

    public void authEntryDone(View view){
        EditText tp = findViewById(R.id.passwd);
        Intent r = new Intent();
        //r.putExtra("PASSWORD", tp.getText().toString() );
        r.putExtra("PASSWORD", "matched" );
        setResult(RESULT_OK, r);
        finish();
    }

    // check if passwords are same
    public boolean confirmPassword(String firstPassword, String secondPassword){
        if(firstPassword.equals(secondPassword)) {
            return true;
        }
        return false;
    }


}

