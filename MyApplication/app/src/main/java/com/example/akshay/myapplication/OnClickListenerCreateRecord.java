package com.example.akshay.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

/**
 * Created by akshay on 23/12/15.
 */
public class OnClickListenerCreateRecord implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextStudentFirstname = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextStudentEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Add Record")
                .setPositiveButton("Add",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }

                        }).show();


    }
}