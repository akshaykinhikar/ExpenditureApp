package com.example.akshay.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

/**
 * Created by akshay on 25/12/15.
 */
public class OnLongClickListenerRecord implements View.OnLongClickListener {

    @Override
    public boolean onLongClick(View view) {
        Context context;
        String id;

        context = view.getContext();
        id = view.getTag().toString();

        final CharSequence[] items = { "Edit", "Delete" };

        new AlertDialog.Builder(context).setTitle("Student Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {

                        dialog.dismiss();

                    }
                }).show();

        return false;
    }

}