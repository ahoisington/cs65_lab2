package com.example.savannah.lab1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by acaciah on 10/1/17.
 */

public class PasswordAuthDiag extends DialogFragment {

    private String name;
    private String password_conf;

    public String getPass(){
        return password_conf;
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface AuthDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);
        void onDialogNegativeClick(DialogFragment dialog);
    }

    AuthDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.password_auth_label);
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        //   "final" is important for the closure to be created in the inner class
        final View dialog_view = inflater.inflate(R.layout.password_auth, null);

        builder.setView(dialog_view)
                .setPositiveButton(R.string.password_auth_submit_button, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Log.d("DIALOG", "positive clicked");

                        // collect strings
                        TextView tu = dialog_view.findViewById(R.id.password_auth_textview);
                        password_conf = tu.getText().toString();

                        mListener.onDialogPositiveClick(PasswordAuthDiag.this);
                        dialog.dismiss();
                    }
                })
                // Add action buttons
//                .setPositiveButton(R.string.password_auth_submit_button, new DialogInterface.OnKeyListener() {
//
//                    public void onClick(DialogInterface dialog, int id) {
//                        Log.d("DIALOG", "positive clicked");
//
//                        // collect strings
//                        TextView tu = dialog_view.findViewById(R.id.password_auth_textview);
//                        password_conf = tu.getText().toString();
//
//                        mListener.onDialogPositiveClick(PasswordAuthDiag.this);
//                    }
//
//                    ;
//
//                    @Override
//                    public boolean onKey(DialogInterface dialogInterface, int keyCode, KeyEvent event) {
////                        if( keyCode == KeyEvent.KEYCODE_ENTER ) {
////                            if( event.getAction() == KeyEvent.ACTION_UP ) {
////                                searchFor(searchText.getText().toString());
////                            }
////                            return true;
////                        }
////                        return false;
//                        return true;
//                    }
//                }
                .setNegativeButton(R.string.account_label, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });


        return builder.create();
    }


//    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        // Verify that the host activity implements the callback interface
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            mListener = (AuthDialogListener) activity;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(activity.toString()
//                    + " must implement NoticeDialogListener");
//        }
//        Log.d("DIALOG", "attached");
//    }


}
