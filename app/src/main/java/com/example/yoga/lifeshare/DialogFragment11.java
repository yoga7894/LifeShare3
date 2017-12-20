package com.example.yoga.lifeshare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Yoga on 8/21/2017.
 */

public class DialogFragment11 extends DialogFragment {
    int V;
    Communicator1 c;


    String bloodgroups[]={"A+","B+","AB+","O+","A-","B-","AB-","O-","All"};
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        c= (Communicator1) getActivity();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick BloodGroups");
       builder.setItems(bloodgroups, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               V=i;
               String choice=bloodgroups[i];
                c.respond1(choice);

           }
       });
        Dialog dialog=builder.create();
        return  dialog;
    }

    public interface Communicator1
    {
        public void respond1(String k);
    }

}
