
package com.example.savannah.lab1;

        import android.os.Bundle;
        import android.app.Activity;
        import android.app.Fragment;
        import android.app.FragmentManager;
        import android.view.View;
        import android.widget.Toast;
        import android.widget.Button;
/**
 * This activity handles the pop up for password confirmation
 * Based on the activity example in http://www.cs.dartmouth.edu/~sergey/cs65/examples/Dialog/
 */

public class PasswordAuthActivity extends Activity {

    private static final int DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setContentView(R.layout.password_auth);

        final Button submitBtn = findViewById(R.id.submit_button);
////        b.setAlpha(.3f);
////        b.setClickable(false);
        submitBtn.setEnabled(true);
//        final EditText tu = findViewById(R.id.username);
//        tu.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {
//                Log.d("TEXT", "afterTextChanged");
//            }
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                Log.d("TEXT", "beforeTextChanged: '" + s.toString() + "'");
//
//            }
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                b.setAlpha(1f);
////                b.setClickable(true);
//                b.setEnabled(true);
//            }
//        });
//
//        final EditText tp = findViewById(R.id.passwd);
//        // same as above here
//        tp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    Log.d("PASSBOX", "lost focus");
//                } else {
//                    Log.d("PASSBOX", "got focus");
//                }
//            }
//        });
//
//        // catch Enter typed in this box
//        tp.setOnKeyListener(new View.OnKeyListener() {
//            public boolean onKey(View v, int keyCode, KeyEvent event) {
//                // If the event is a key-down event on the "enter" button
//                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
//                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
//                    // Perform action on key press
//                    Toast.makeText(AuthActivity.this,
//                            tp.getText(),
//                            Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//                return false;
//            }
//        });


    }
}
