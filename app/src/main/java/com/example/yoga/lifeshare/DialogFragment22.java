package com.example.yoga.lifeshare;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by Yoga on 9/4/2017.
 */

public class DialogFragment22 extends DialogFragment {

    View view;
    ListView listView;
    Placer2 placer;
    String[] places;
    DatabaseReference mRef;
    final ArrayList<String> list = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.df2,null);
        placer= (Placer2) getActivity();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView= (ListView) view.findViewById(R.id.list);
        list.add("All");
        mRef= FirebaseDatabase.getInstance().getReferenceFromUrl("https://lifeshare-5871e.firebaseio.com/UserRelated/UserPlaces");

        mRef.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    System.out.println("BOBO" + postSnapshot.getKey());
                    list.add(postSnapshot.getKey());

                }
                places = list.toArray(new String[list.size()]);
                System.out.print("LIL" + places[0]);
                try {
                    final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, places);
                    listView.setAdapter(itemsAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String w = places[i];
                            placer.Placerr2(w);
                            dismiss();
                        }
                    });
                }catch (Exception e)
                {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public interface Placer2
    {
        public void Placerr2(String k);
    }
}
