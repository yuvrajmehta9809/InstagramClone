package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.LogOutCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class WelcomeActivity extends AppCompatActivity {

   public TextView txtName;
   private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        txtName = findViewById(R.id.txtName);
        txtName.setText( "Welcome "+ParseUser.getCurrentUser().getUsername());
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ParseUser.logOutInBackground(new LogOutCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null){

                            Intent i = new Intent(WelcomeActivity.this,SignUpLoginActivity.class);
                            startActivity(i);
                        }

                    }
                });
            }
        });
    }
}
