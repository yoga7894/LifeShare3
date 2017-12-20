package com.example.yoga.lifeshare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Yoga on 8/23/2017.
 */

public class FragB extends Fragment implements View.OnClickListener {

    View view;
    DialogFragment1.Communicator c;
    EditText bloseek,place,email1,phone,desc;
    Spinner spinner;
    Button button;
    String bloodgroups[]={"A+","B+","AB+","O+","A-","B-","AB-","O-"};
    private DatabaseReference mRef,mRef2,mRef3;
    String name,email;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.frag_b,container,false);
        name=this.getArguments().getString("name");
        Log.v("FEE","EEEE"+name);
        c= (DialogFragment1.Communicator) getActivity();
        email=this.getArguments().getString("email");
        Log.i("See",""+name);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Request For Blood");
        bloseek= (EditText) getActivity().findViewById(R.id.bloseek);
        place= (EditText) getActivity().findViewById(R.id.place);
        spinner= (Spinner) getActivity().findViewById(R.id.spinner);
        email1= (EditText) getActivity().findViewById(R.id.email1);
        phone= (EditText) getActivity().findViewById(R.id.phone);
        desc= (EditText) getActivity().findViewById(R.id.desc);
        button= (Button) getActivity().findViewById(R.id.button);

        mRef= FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeeker");
        mRef2= FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeekerPlace");
        mRef3= FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeekerBloodGroup");



        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,bloodgroups);
        spinner.setAdapter(aa);

        button.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.button)
        {

            String Bseek=bloseek.getText().toString();
            String Bplace=place.getText().toString();
            String bg=spinner.getSelectedItem().toString();
            String Email=email1.getText().toString();
            String Phone=phone.getText().toString();
            String Desc= desc.getText().toString();
            try {

                if (!TextUtils.isEmpty(Bseek) && !TextUtils.isEmpty(Bplace)&&!TextUtils.isEmpty(Email)&&!TextUtils.isEmpty(Phone)&&!TextUtils.isEmpty(Desc)) {
                    DatabaseReference mchild = mRef.child(Bseek);
                    mchild.child("Name").setValue(Bseek);
                    mchild.child("place").setValue(Bplace);
                    mchild.child("Bgroup").setValue(bg);
                    mchild.child("posted").setValue(name);
                    mchild.child("Email").setValue(Email);
                    mchild.child("Phone").setValue(Phone);
                    mchild.child("Desc").setValue(Desc);

                    mchild = mRef2.child(Bplace).child(Bseek);
                    mchild.child("Name").setValue(Bseek);
                    mchild.child("place").setValue(Bplace);
                    mchild.child("Bgroup").setValue(bg);
                    mchild.child("posted").setValue(name);
                    mchild.child("Email").setValue(Email);
                    mchild.child("Phone").setValue(Phone);
                    mchild.child("Desc").setValue(Desc);

                    mchild = mRef3.child(bg).child(Bseek);
                    mchild.child("Name").setValue(Bseek);
                    mchild.child("place").setValue(Bplace);
                    mchild.child("Bgroup").setValue(bg);
                    mchild.child("posted").setValue(name);
                    mchild.child("Email").setValue(Email);
                    mchild.child("Phone").setValue(Phone);
                    mchild.child("Desc").setValue(Desc);



                    Toast.makeText(getContext(), "Requested..", Toast.LENGTH_SHORT).show();
                    c.respond("All");
                }

                }catch(Exception e)
                {
                    Toast.makeText(getActivity(), "Error Uploading..", Toast.LENGTH_SHORT).show();
                }



        }
    }


}
