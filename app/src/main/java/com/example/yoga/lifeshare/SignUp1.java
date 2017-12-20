package com.example.yoga.lifeshare;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp1 extends AppCompatActivity {
    private DatabaseReference mRef,mRef2,mRef3;

    private EditText uname,uemail,uaddress,ucity,ustate,udob,upass,uphone;
    private Spinner bg;
    private Button signup;

    String bloodgroups[]={"A+","B+","AB+","O+","A-","B-","AB-","O-"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up1);
        mRef= FirebaseDatabase.getInstance().getReference().child("UserRelated").child("Users");
        mRef2=FirebaseDatabase.getInstance().getReference().child("UserRelated").child("UserBloodGroups");
        mRef3=FirebaseDatabase.getInstance().getReference().child("UserRelated").child("UserPlaces");
        uname= (EditText) findViewById(R.id.uname);
        uemail= (EditText) findViewById(R.id.uemail);
        uphone= (EditText) findViewById(R.id.uphone);
        uaddress= (EditText) findViewById(R.id.uaddress);
        ucity= (EditText) findViewById(R.id.city);
        ustate= (EditText) findViewById(R.id.State);
        udob= (EditText) findViewById(R.id.DOB);
        bg= (Spinner) findViewById(R.id.bg);
        signup= (Button) findViewById(R.id.signup);
        upass= (EditText) findViewById(R.id.pass);


        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_list_item_1,bloodgroups);
        bg.setAdapter(aa);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=uname.getText().toString();
                String email=uemail.getText().toString();
                String address=uaddress.getText().toString();
                String city=ucity.getText().toString();
                String phone=uphone.getText().toString();
                String state=ustate.getText().toString();
                String dob=udob.getText().toString();
                String BloodGroup= bg.getSelectedItem().toString();
                String pass=upass.getText().toString();

                if (!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(address)&&!TextUtils.isEmpty(city)&&!TextUtils.isEmpty(state)&&!TextUtils.isEmpty(dob)&&!TextUtils.isEmpty(pass)) {

                    UploadAdapter adapter = new UploadAdapter();
                    adapter.setName(name);
                    adapter.setEmail(email);
                    adapter.setAddress(address);
                    adapter.setCity(city);
                    adapter.setState(state);
                    adapter.setDob(dob);
                    adapter.setBloodGroup(BloodGroup);
                    adapter.setPhone(phone);
                    adapter.setPassword(pass);

                    mRef.child(name).setValue(adapter);
                    mRef2.child(BloodGroup).child(name).setValue(adapter);
                    mRef3.child(city).child(name).setValue(adapter);

                    startActivity(new Intent(SignUp1.this, MainActivity.class));
                    finish();
                }
                else
                {
                    Toast.makeText(SignUp1.this, "Empty columns", Toast.LENGTH_SHORT).show();
                }

            }
        });






    }
}
