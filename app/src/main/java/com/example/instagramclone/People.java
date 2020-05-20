package com.example.instagramclone;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class People extends Fragment {

    // DECLARING UI COMPONENTS.

    ListView listView;
    ArrayAdapter arrayAdapter;
    ArrayList arrayList;



    public People() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);

        // INITIALISE UI COMPONENTS.

        listView = view.findViewById(R.id.listView);
        arrayList = new ArrayList();
        // CREATING AN ARRAYADAPTER TO HOLD THE ARRAYLIST AS THE LISTVIEW.
        arrayAdapter = new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,arrayList);

        //GENERATE PARSEQUERY TO GET USERNAMES AND STORE THEM IN THE ARRAYLIST.

        ParseQuery<ParseUser> parseQuery = ParseUser.getQuery();
        parseQuery.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());

        parseQuery.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null){

                    if(objects.size() > 0){

                        //ADDING THE USERNAMES TO THE ARRAYLIST.
                        for (ParseUser users : objects){

                            arrayList.add(users.getUsername());
                        }
                        listView.setAdapter(arrayAdapter);
                    }
                }
            }
        });

        return view;
    }

}
