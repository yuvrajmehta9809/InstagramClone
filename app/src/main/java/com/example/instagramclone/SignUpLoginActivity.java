package com.example.instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpLoginActivity extends AppCompatActivity {

    Button btnLogin,btnSignUp;
    EditText edtSName,edtLName,edtLPassword,edtSPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_login_activity);
       btnSignUp = findViewById(R.id.btnSignUp);
       btnLogin = findViewById(R.id.btnLogIn);
       edtSName = findViewById(R.id.editTextSUserName);
        edtSPassword= findViewById(R.id.editTextSPassword);
        edtLName= findViewById(R.id.editTextLoginUName);
       edtLPassword = findViewById(R.id.editLoginPassword);

// SIGNING UP THE NEW USER

       btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               // making parse user to save the first time signed up users

               final ParseUser appUser = new ParseUser();
               appUser.setUsername(edtSName.getText().toString());
               appUser.setPassword(edtSPassword.getText().toString());

               appUser.signUpInBackground(new SignUpCallback() {
                   @Override
                   public void done(ParseException e) {
                       // if there is no exception then on signing up we
                       // are switched to another activity
                       if(e == null){

                           Intent i = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                           startActivity(i);


                       }

                   }
               });
           }
       });

       // logiing in the existing user (retriving data from parse server)

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseUser.logInInBackground(edtLName.getText().toString(), edtLPassword.getText()
                        .toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {

                        // CHECKING IF THE CREDENTIALS ARE CORRECT
                        // SO SWITCH TO ANOTHER ACTIVITY

                        if (e == null){

                            Intent i = new Intent(SignUpLoginActivity.this,WelcomeActivity.class);
                            startActivity(i);
                        }
                        else {
                            FancyToast.makeText(SignUpLoginActivity.this,"wrong credentials",
                                    FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();
                        }

                    }
                });
            }
        });
    }
}
