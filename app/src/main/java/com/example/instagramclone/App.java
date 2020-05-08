package com.example.instagramclone;

import android.app.Application;
import android.view.View;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ZfxM6Ep4RhW3T3dQk7CbKn4RPUIpPxtAwZYtkbSV")
                // if defined
                .clientKey("rYtDtSPMFBYT5wobQ4exMDlGgT1PoUpPt3Ru4UVp")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

    }



