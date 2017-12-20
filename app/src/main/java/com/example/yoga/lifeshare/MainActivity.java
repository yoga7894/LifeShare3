package com.example.yoga.lifeshare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private EditText uemail, upass;
    private TextView usignup;
    private Button login;
    private DatabaseReference mRef, mRef2;
    String email, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uemail = (EditText) findViewById(R.id.uemail);
        upass = (EditText) findViewById(R.id.upass);
        usignup = (TextView) findViewById(R.id.usignup);
        login = (Button) findViewById(R.id.login);
        mRef=FirebaseDatabase.getInstance().getReference().child("UserRelated").child("Users");

        usignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp1.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 email=uemail.getText().toString();
                pass=upass.getText().toString();

                if (!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(pass)) {
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.hasChild(email)) {
                                mRef2 = mRef.child(email);
                                mRef2.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        UploadAdapter adapter = dataSnapshot.getValue(UploadAdapter.class);

                                        if (adapter.getPassword().equals(pass)) {
                                            Intent i = new Intent(MainActivity.this, MenuActivity.class);
                                            i.putExtra("name", adapter.getName());
                                            i.putExtra("email", adapter.getEmail());
                                            startActivity(i);
                                            finish();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }
                else
                {
                    Toast.makeText(MainActivity.this, "Empty columns", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}


