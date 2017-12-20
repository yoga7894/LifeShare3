package com.example.yoga.lifeshare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Yoga on 8/21/2017.
 */

public class DialogFragment1 extends DialogFragment {
    int V;
    Communicator c;


    String bloodgroups[]={"A+","B+","AB+","O+","A-","B-","AB-","O-","All"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        c= (Communicator) getActivity();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick BloodGroups");
       builder.setItems(bloodgroups, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               V=i;
               String choice=bloodgroups[i];
                c.respond(choice);

           }
       });
        Dialog dialog=builder.create();
        return  dialog;
    }

    public interface Communicator
    {
        public void respond(String k);
    }

}
