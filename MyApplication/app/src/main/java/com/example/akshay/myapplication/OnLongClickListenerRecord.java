package com.example.akshay.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by akshay on 25/12/15.
 */
public class OnLongClickListenerRecord implements View.OnLongClickListener {

    Context context;

    public  OnLongClickListenerRecord(Context context){
        this.context = context;
    }

    @Override
    public boolean onLongClick(View view) {
        Context context;
        final String id;

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Edit", "Delete" };


        new AlertDialog.Builder(context).setTitle("Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        }

                        dialog.dismiss();


                    }
                }).show();

        return false;
    }

    public void editRecord(final int recordId) {

//        Context context;

//        final context =  getContext();

        final TableControllerRecords tableControllerRecord = new TableControllerRecords(context);
        ObjectRecord objectRecord = tableControllerRecord.readSingleRecord(recordId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.student_input_form, null, false);

        final EditText editTextRecordFirstname = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextRecordEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);

        editTextRecordFirstname.setText(objectRecord.firstname);
        editTextRecordEmail.setText(objectRecord.email);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ObjectRecord objectRecord = new ObjectRecord();
                                objectRecord.id = recordId;
                                objectRecord.firstname = editTextRecordFirstname.getText().toString();
                                objectRecord.email = editTextRecordEmail.getText().toString();

                                boolean updateSuccessful = tableControllerRecord.update(objectRecord);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Record record was updated.", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Unable to update record.", Toast.LENGTH_SHORT).show();
                                }

                                ((MainActivity) context).countRecords();
                                ((MainActivity) context).readRecords();

                                dialog.cancel();
                            }

                        }).show();

    }//editRecord

}

