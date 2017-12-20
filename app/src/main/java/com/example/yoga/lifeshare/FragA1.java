package com.example.yoga.lifeshare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Yoga on 9/1/2017.
 */
public class FragA1 extends android.support.v4.app.Fragment {

    View view;
    String name,email,choice;
    DatabaseReference databaseReference;
    RecyclerView blog_list;
    String post_key;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.frag_a1,container,false);
        setHasOptionsMenu(true);
        name=this.getArguments().getString("name");
        email=this.getArguments().getString("email");
        try {
            choice = this.getArguments().getString("choice");
        }catch (Exception e)
        {
           //Nothing to worry
        }
        return view;
    }


    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Blood Requests");
        databaseReference= FirebaseDatabase.getInstance().getReference().child("BloodSeekerRelated").child("BloodSeekerBloodGroup").child(choice);
        databaseReference.keepSynced(true);
        blog_list= (RecyclerView) view.findViewById(R.id.blog_list);
        blog_list.setHasFixedSize(true);
        blog_list.setLayoutManager(new LinearLayoutManager(getActivity()));



        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(
                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                databaseReference

        ) {


            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model,final int position) {

                viewHolder.setTitle(model.getName() + "," + model.getPlace());
                viewHolder.setDescription("Posted by " + model.getPosted());
                viewHolder.setImage(model.getBgroup());
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        post_key = getRef(position).getKey();

                        Intent i = new Intent(getActivity(), ViewRequest1.class);
                        i.putExtra("choice",choice);
                        i.putExtra("key", post_key);
                        i.putExtra("name", name);
                        i.putExtra("email", email);
                        startActivity(i);
                        return false;
                    }
                });
            }
        };

        blog_list.setAdapter(firebaseRecyclerAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fraga_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.settingsA)
        {
            DialogFragment1 f=new DialogFragment1();
            f.show(getActivity().getFragmentManager(),"LOl");

        }
        else if (item.getItemId()==R.id.settingsB)
        {
            DialogFragment2 f=new DialogFragment2();
            f.show(getActivity().getFragmentManager(),"Heu");
        }


        return super.onOptionsItemSelected(item);
    }



    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDescription(String description) {
            TextView post_desc = (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(description);
        }
        public void setImage(String data)
        {
            ImageView im= (ImageView) mView.findViewById(R.id.post_image);
            switch (data)
            {
                case "A+":
                {
                    im.setImageResource(R.drawable.ap);
                    break;
                }

                case "B+":
                {
                    im.setImageResource(R.drawable.bp);
                    break;
                }

                case "O+":
                {
                    im.setImageResource(R.drawable.op);
                    break;
                }

                case "AB+":
                {
                    im.setImageResource(R.drawable.abp);
                    break;
                }

                case "A-":
                {
                    im.setImageResource(R.drawable.an);
                    break;
                }
                case "B-":
                {
                    im.setImageResource(R.drawable.bn);
                    break;
                }
                case "AB-":
                {
                    im.setImageResource(R.drawable.abn);
                    break;
                }
                case "O-":
                {
                    im.setImageResource(R.drawable.on);
                    break;
                }
            }
        }

    }
}
