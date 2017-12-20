package com.example.yoga.lifeshare;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Yoga on 8/29/2017.
 */

public class FragD extends Fragment {

    Blogger blogger;
    private ImageView imageView;
    private EditText ed1,ed2;
    private Button button;
    private static final  int GAL_INT=2;
    private Uri uri =null;
    String title;
    String desc,name,email;
    private StorageReference mStorsge;
    private DatabaseReference mDatabase;
    private ProgressDialog progress;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_d,container,false);
        blogger= (Blogger) getActivity();
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Upload to Blog");
        imageView= (ImageView) view.findViewById(R.id.imageView);
        ed1= (EditText) view.findViewById(R.id.editText);
        ed2= (EditText) view.findViewById(R.id.editText2);
        button= (Button) view.findViewById(R.id.button);
        mStorsge= FirebaseStorage.getInstance().getReference();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog");
        progress=new ProgressDialog(getActivity());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,GAL_INT);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPosting();
            }
        });
    }

    private void startPosting() {
        title=ed1.getText().toString();
        desc=ed2.getText().toString();

        if (!TextUtils.isEmpty(title)&&!TextUtils.isEmpty(desc)&&uri!=null)
        {       progress.setMessage("Uploading..");
            progress.setCancelable(false);
            progress.show();
            try {
                StorageReference filepath = mStorsge.child("blog_pics").child(uri.getLastPathSegment());
                filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Uri downloadUri = taskSnapshot.getDownloadUrl();
                        DatabaseReference newPost = mDatabase.push();
                        newPost.child("Title").setValue(title);
                        newPost.child("Description").setValue(desc);
                        newPost.child("image").setValue(downloadUri.toString());
                        progress.dismiss();
                        blogger.Blogee();

                    }
                });
            }catch (Exception e)
            {
                Toast.makeText(getActivity(), "FIREBASE IS BUSY NOW", Toast.LENGTH_SHORT).show();
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
           if (requestCode==GAL_INT&&resultCode==RESULT_OK)
            {
                uri=data.getData();
                imageView.setImageURI(uri);
            }

    }
    public interface Blogger{
        public void Blogee();

    }
}
