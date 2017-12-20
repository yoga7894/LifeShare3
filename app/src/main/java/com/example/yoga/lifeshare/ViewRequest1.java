package com.example.yoga.lifeshare;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewRequest1 extends AppCompatActivity {
  String post_key,name,email,Reciever,recphone,choice;
    TextView bloseek,place,bg,email1,phone,desc,postedby;
    Button button;
    private DatabaseReference mRef,mDonate,mRef2,mRef3;
    private Blog blog;
    String remo,Bplace,ps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_request);
        post_key=getIntent().getExtras().getString("key");
        choice=getIntent().getExtras().getString("choice");
        name=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");
        mRef=FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeekerBloodGroup").child(choice).child(post_key);
        //Log.v("HEIE","RODDING"+mRef.child(post_key));
        mRef2=FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeeker");
        mRef3=FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeekerPlace");
        mDonate=FirebaseDatabase.getInstance().getReference().child("Donated");
        bloseek= (TextView) findViewById(R.id.bloseek);
        place= (TextView) findViewById(R.id.place);
        bg= (TextView) findViewById(R.id.bg);
        email1= (TextView) findViewById(R.id.email1);
        postedby= (TextView) findViewById(R.id.postedby);
        phone= (TextView) findViewById(R.id.phone);
        desc= (TextView) findViewById(R.id.desc);
        button= (Button) findViewById(R.id.button);

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

               try {
                   blog = dataSnapshot.getValue(Blog.class);
                   bloseek.setText(blog.getName());
                   remo=blog.getName();
                   place.setText(blog.getPlace());
                   Bplace=blog.getPlace();
                   bg.setText(blog.getBgroup());
                   email1.setText(blog.getEmail());
                   Reciever=blog.getEmail();
                   recphone=blog.getPhone();
                   phone.setText(blog.getPhone());
                   desc.setText(blog.getDesc());
                   postedby.setText(blog.getPosted());
                   ps=blog.getPosted();
               }
               catch (Exception e)
               {
                   Toast.makeText(ViewRequest1.this, "Successful in Donation", Toast.LENGTH_SHORT).show();
               }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ViewRequest1.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

            }
        });



        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + Reciever));

                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));

            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDonate.child(name).child(remo).setValue(blog);
                mRef.removeValue();
                mRef2.child(remo).removeValue();
                mRef3.child(Bplace).removeValue();

            }
        });

        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent  intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:"+recphone));
                startActivity(intent);
            }
        });




    }
}
