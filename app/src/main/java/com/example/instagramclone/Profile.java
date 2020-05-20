package com.example.instagramclone;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import static android.content.Context.INPUT_METHOD_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    // DECLARE UI ELEMENTS
    EditText edtProfileName,edtProfileBio,edtProfileProfession,edtprofileHobbies,edtFavouriteSports;
    Button btnUpdateInfo;


    public Profile() {
        // Required empty public constructor
    }

// onCreateView is similar to onCreate in activity.
    //inflate method is similar to setContentView in Activity.
    // this method returns the layout file which is attached to the fragment.
    //Attach to root is false because we already have a layout file attached
    // to this class(Fragment).

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // this is not an activity so findViewById cannot be called directly.
        // So the method is called on View object which contains all the views
        // of the layout file.

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProffession);
        edtprofileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtFavouriteSports = view.findViewById(R.id.edtFavSport);
        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);

        // GET THE CURRENT USER.
        final ParseUser parseUser = ParseUser.getCurrentUser();

        //GET THE DATA BACK FROM THE SERVER WHEN USER LOGIN'S AGAIN.
        if(parseUser.get("profileName") == null && parseUser.get("Bio") == null
        && parseUser.get("Profession") == null && parseUser.get("Hobbies") == null
        && parseUser.get("FavouriteSport") == null){

            edtProfileName.setText("");
            edtProfileBio.setText("");
            edtProfileProfession.setText("");
            edtprofileHobbies.setText("");
            edtFavouriteSports.setText("");

        }
        else {

            edtProfileName.setText(parseUser.get("profileName") + "");
            edtProfileBio.setText(parseUser.get("Bio") + "");
            edtProfileProfession.setText(parseUser.get("Profession") + "");
            edtprofileHobbies.setText(parseUser.get("Hobbies") + "");
            edtFavouriteSports.setText(parseUser.get("FavouriteSport") + "");
        }

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // save the data that user enters into textfield
                // in the parse server.

                parseUser.put("profileName",edtProfileName.getText().toString());
                parseUser.put("Bio",edtProfileBio.getText().toString());
                parseUser.put("Profession",edtProfileProfession.getText().toString());
                parseUser.put("Hobbies",edtprofileHobbies.getText().toString());
                parseUser.put("FavouriteSport",edtFavouriteSports.getText().toString());



                // SAVE DATA IN THE SERVER.
                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if(e == null){

                            FancyToast.makeText(getContext(),"Info Updated", FancyToast.LENGTH_SHORT,
                            FancyToast.INFO,true).show();
                        }
                        else {
                            FancyToast.makeText(getContext(),e.getMessage(), FancyToast.LENGTH_SHORT,
                                    FancyToast.INFO,true).show();

                        }

                    }
                });
            }
        });

        return view;
    }


}
