package com.example.akshay.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
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

        final EditText editTextRecordPrice = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextRecordNote = (EditText) formElementsView.findViewById(R.id.editTextEmail);
        final RadioGroup radioGroupCategory = (RadioGroup) formElementsView.findViewById(R.id.radioGroup1Category);
        final CalendarView dateOfRecordDB = (CalendarView)formElementsView.findViewById(R.id.calender_record_input);


        editTextRecordPrice.setText(objectRecord.price);
        editTextRecordNote.setText(objectRecord.note);
        dateOfRecordDB.setDate(objectRecord.dateOfRecord);
        
        
        String storedCategory = objectRecord.categoryOfRecord;
        Log.d("storedCategory", "" + storedCategory);

        
        String[] catArray = new String[]{"Transports", "Bill", "Glossaries", "Fuel","Other"};

        for ( int i=0; i< catArray.length; i++ ){
           if(catArray[i].equals(storedCategory) ) {
               Log.d("error", "value of i is" + i);
               categoryCountEdit = i;
               Log.d("error", "" + "categoryCountEdit" + categoryCountEdit + "" + i);

               switch (i) {
                   case 0:
                       radioGroupCategory.check(R.id.radioButtonTransport);
                       Log.d("error", "radioGroupCategory: tran" + radioGroupCategory);
                       break;
                   case 1:
                       radioGroupCategory.check(R.id.radioButtonBill);
                       Log.d("error", "radioGroupCategory: bill" + radioGroupCategory);
                       break;
                   case 2:
                       radioGroupCategory.check(R.id.radioButtonGlossaries);
                       Log.d("error", "radioGroupCategory: GLossary" + radioGroupCategory);
                       break;
                   case 3:
                       radioGroupCategory.check(R.id.radioButtonFuel);
                       Log.d("error", "radioGroupCategory: Fuel" + radioGroupCategory);
                       break;
                   case 4:
                       radioGroupCategory.check(R.id.radioButtonOther);
                       Log.d("error", "radioGroupCategory: Other" + radioGroupCategory);
                       break;
               }
           }
        }
        final CalendarView cal = (CalendarView) formElementsView.findViewById(R.id.calender_record_input);

         new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                ObjectRecord objectRecord = new ObjectRecord();
                                objectRecord.id = recordId;
                                objectRecord.price = editTextRecordPrice.getText().toString();
                                objectRecord.note = editTextRecordNote.getText().toString();
                                objectRecord.dateOfRecord =  cal.getDate();;

                                // for category
                                RadioGroup radioGroupCategoy = (RadioGroup) formElementsView.findViewById(R.id.radioGroup1Category);
                                int selectedId = radioGroupCategory.getCheckedRadioButtonId();
                                RadioButton radioItem = (RadioButton) formElementsView.findViewById(selectedId);
                                String category = radioItem.getText().toString();

                                objectRecord.categoryOfRecord = category;
//                                objectRecord.dateOfRecord = "date";

                                boolean updateSuccessful = tableControllerRecord.update(objectRecord);

                                if(updateSuccessful){
                                    Toast.makeText(context, "Record was updated.", Toast.LENGTH_SHORT).show();
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

