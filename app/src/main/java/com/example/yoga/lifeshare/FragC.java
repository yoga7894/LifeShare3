package com.example.yoga.lifeshare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

/**
 * Created by Yoga on 8/23/2017.
 */

public class FragC extends Fragment {

    public String post_key;
    View view;

    private RecyclerView blog_list;
    private DatabaseReference databaseReference;
  //  String name,email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag_c, container, false);
     //   name=this.getArguments().getString("name");
      //  email=this.getArguments().getString("email");
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("Blog");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Blog");
        databaseReference.keepSynced(true);
        blog_list = (RecyclerView) view.findViewById(R.id.blog_list);
        blog_list.setHasFixedSize(true);
        blog_list.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerAdapter<Blog1, BlogViewHolder1> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog1, BlogViewHolder1>(
                Blog1.class,
                R.layout.blog_row_1,
                BlogViewHolder1.class,
                databaseReference

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder1 viewHolder, Blog1 model, int position) {
                post_key = getRef(position).getKey();
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImage(getActivity(), model.getImage());
                viewHolder.mView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {


                        return false;
                    }
                });

            }
        };
        blog_list.setAdapter(firebaseRecyclerAdapter);


    }


    public static class BlogViewHolder1 extends RecyclerView.ViewHolder
    {
        View mView;

        public BlogViewHolder1(View itemView) {
            super(itemView);
            mView=itemView;

        }

        public void setTitle(String title)
        {
            TextView post_title= (TextView) mView.findViewById(R.id.post_title);
            post_title.setText(title);
        }

        public void setDescription(String description)
        {
            TextView post_desc= (TextView) mView.findViewById(R.id.post_desc);
            post_desc.setText(description);
        }
        public void setImage(final FragmentActivity ctx, final String image)
        {
            final ImageView post_image= (ImageView) mView.findViewById(R.id.post_image);
//            Picasso.with(ctx).load(image).into(post_image);
            Picasso.with(ctx).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    Picasso.with(ctx).load(image).into(post_image);
                }
            });


        }


    }


}
