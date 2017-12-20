package com.example.yoga.lifeshare;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRequest extends AppCompatActivity {
    TextView bloseek,place,bg,email1,phone;
    DatabaseReference mRef;
    UserAdapter adapter;
    String post_key,name,email,Reciever,recphone;
    Button button;
    Returning returning1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_request);
        post_key=getIntent().getExtras().getString("key");
        Log.v("LOLO","KIKI"+post_key);

        name=getIntent().getExtras().getString("name");
        email=getIntent().getExtras().getString("email");
        mRef= FirebaseDatabase.getInstance().getReference().child("UserRelated").child("Users");
        bloseek= (TextView) findViewById(R.id.bloseek);
        place= (TextView) findViewById(R.id.place);
        bg= (TextView) findViewById(R.id.bg);
        email1= (TextView) findViewById(R.id.email1);
        phone= (TextView) findViewById(R.id.phone);
        button= (Button) findViewById(R.id.button);


        mRef.child(post_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try {
                    adapter=dataSnapshot.getValue(UserAdapter.class);
                    bloseek.setText(adapter.getName());
                    Reciever=adapter.getEmail();
                    place.setText(adapter.getCity());
                    bg.setText(adapter.getBloodGroup());
                    email1.setText(adapter.getEmail());
                    recphone=adapter.getPhone();
                    phone.setText(adapter.getPhone());
                }
                catch (Exception e)
                {
                    Toast.makeText(UserRequest.this, "Successful in Donation", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(UserRequest.this, "Something went wrong...", Toast.LENGTH_SHORT).show();

            }
        });



        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + Reciever));

                startActivity(Intent.createChooser(emailIntent, "Chooser Title"));

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

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
    public interface Returning{
        public void rodding();
    }
}
