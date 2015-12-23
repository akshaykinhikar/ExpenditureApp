package com.example.akshay.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by akshay on 23/12/15.
 */
public class OnClickListenerCreateRecord implements View.OnClickListener {
    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextFirstname = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Add Record")
                .setPositiveButton("Add",


                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String firstname = editTextFirstname.getText().toString();
                                String email = editTextEmail.getText().toString();

                                ObjectRecord objectRecord = new ObjectRecord();
                                objectRecord.firstname= firstname;
                                objectRecord.email= email;
                                boolean createSuccessful = new TableControllerStudent(context).create(objectRecord);


                                if(createSuccessful){
                                    Toast.makeText(context, "Record was saved.", Toast.LENGTH_SHORT).show();
//                                    countRecords();
                                }else{
                                    Toast.makeText(context, "Unable to save Record.", Toast.LENGTH_SHORT).show();
                                }

                                dialog.cancel();
                            }

                        }).show();

    }
}