package com.example.akshay.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by akshay on 25/12/15.
 */
public class OnLongClickListenerRecord implements View.OnLongClickListener {

    Context context;
    int categoryCountEdit;
    RadioButton selectedRadioForEdit;
    RadioButton selectedRadioForEditId;

    public OnLongClickListenerRecord(Context context){
        this.context = context;
    }


    @Override
    public boolean onLongClick(View view) {
        final Context context;
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
                        else if (item == 1) {

                            boolean deleteSuccessful = new TableControllerRecords(context).delete(id);

                            if (deleteSuccessful){
                                Toast.makeText(context, " record was deleted.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(context, "Unable to delete record.", Toast.LENGTH_SHORT).show();
                            }

                            ((MainActivity) context).countRecords();
                            ((MainActivity) context).readRecords();

                        }

                        dialog.dismiss();

                    }
                }).show();

        return false;
    }

    public void editRecord(final int recordId) {


        final TableControllerRecords tableControllerRecord = new TableControllerRecords(context);
        ObjectRecord objectRecord = tableControllerRecord.readSingleRecord(recordId);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.record_input_form, null, false);

        final EditText editTextRecordFirstname = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextRecordEmail = (EditText) formElementsView.findViewById(R.id.editTextEmail);
        final RadioGroup radioGroupCategory = (RadioGroup) formElementsView.findViewById(R.id.radioGroup1Category);  //have to look

        editTextRecordFirstname.setText(objectRecord.price);
        editTextRecordEmail.setText(objectRecord.note);


        String[] catArray = new String[]{"Transports", "Bill", "Glossaries", "Fuel","Other"};

        for ( int i=0; i<= catArray.length; i++ ){
           if(catArray[i] == radioGroupCategory.toString()){
               categoryCountEdit = i;
           }
            switch (i){
                case 1:
                    selectedRadioForEditId = (RadioButton) formElementsView.findViewById(R.id.radioButtonTransport);
                    break;
                case 2:
                    selectedRadioForEditId = (RadioButton) formElementsView.findViewById(R.id.radioButtonBill);
                    break;
                case 3:
                    selectedRadioForEditId = (RadioButton) formElementsView.findViewById(R.id.radioButtonGlossaries);
                    break;
                case 4:
                    selectedRadioForEditId = (RadioButton) formElementsView.findViewById(R.id.radioButtonFuel);
                    break;
                case 5:
                    selectedRadioForEditId = (RadioButton) formElementsView.findViewById(R.id.radioButtonOther);
                    break;
            }
        }


        //boolean selected = radioGroupCategory.isSelected(selectedRadioForEditId);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ObjectRecord objectRecord = new ObjectRecord();
                                objectRecord.id = recordId;
                                objectRecord.price = editTextRecordFirstname.getText().toString();
                                objectRecord.note = editTextRecordEmail.getText().toString();
                                objectRecord.categoryOfRecord = "";
                                objectRecord.dateOfRecord = "date";

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

