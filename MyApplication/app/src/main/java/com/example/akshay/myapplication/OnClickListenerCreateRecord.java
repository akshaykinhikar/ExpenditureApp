package com.example.akshay.myapplication;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by akshay on 23/12/15.
 */
public class OnClickListenerCreateRecord implements View.OnClickListener {
    Context context;
    public OnClickListenerCreateRecord(Context context){
        this.context = context;
    }

    private RadioGroup radioGroupCategoty;
    private RadioButton radioItem;

    @Override
    public void onClick(View view) {
        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.record_input_form, null, false);

        final EditText editTextPrice = (EditText) formElementsView.findViewById(R.id.editTextFirstname);
        final EditText editTextNote = (EditText) formElementsView.findViewById(R.id.editTextEmail);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Add Record")
                .setPositiveButton("Add",


                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                // for category
                                radioGroupCategoty = (RadioGroup) formElementsView.findViewById(R.id.radioGroup1Category);
                                int selectedId = radioGroupCategoty.getCheckedRadioButtonId();
                                radioItem = (RadioButton) formElementsView.findViewById(selectedId);


                                String price = editTextPrice.getText().toString();
                                String note = editTextNote.getText().toString();
                                String category = radioItem.getText().toString();
                                String date = "date_text";




                                ObjectRecord objectRecord = new ObjectRecord();
                                objectRecord.price= price;
                                objectRecord.note= note;
                                objectRecord.categoryOfRecord = category;
                                objectRecord.dateOfRecord = date;

                                Log.d("record:", category );

                                boolean createSuccessful = new TableControllerRecords(context).create(objectRecord);

                                ((MainActivity) context).readRecords();


                                if(createSuccessful){
                                    Toast.makeText(context, "Record was saved.", Toast.LENGTH_SHORT).show();
                                    ((MainActivity) context).countRecords();
                                }else{
                                    Toast.makeText(context, "Unable to save Record.", Toast.LENGTH_SHORT).show();
                                }



                                dialog.cancel();
                            }

                        }).show();

    }
}