package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    //DECLARING UI COMPONENTS
Button btnLogin,btnSignUp;
EditText edtEmail,edtUserName,edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("SignUp");
        // INITIALISING UI COMPONENTS.
        btnLogin = findViewById(R.id.btnLogin2);
        btnSignUp = findViewById(R.id.btnSignUP);
        edtEmail = findViewById(R.id.edtEmail);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);

        // USING ENTER KEY ON KEYBOARD TO SIGNUP THE USER.
        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnSignUp);
                }
                return false;
            }
        });

        //setting on click listener for buttons.
        btnSignUp.setOnClickListener(SignUp.this);
        btnLogin.setOnClickListener(SignUp.this);
        // IF A USER IS ALREADY LOGGED IN THEN BEFORE CLICKING ON
        // SIGNUP OR LOGIN BUTTON HE/SHE MUST BE LOGGED OUT.

        if (ParseUser.getCurrentUser() != null){

            ParseUser.getCurrentUser().logOut();
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //WHEN SIGNUP BUTTON IS TAPPED
            // CREATING A PAESEUSER OBJECT TO SET CREDENTIALS
            // OF THE USER AND FOR SIGNING UP

            case R.id.btnSignUP:
                // CHECK IF TEXT FIELDS ARE FILLED THEN ONLY
                // SIGN UP THE USER
                if (edtEmail.getText().toString().equals("") ||
                        edtUserName.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(SignUp.this, "username,passwordand email are required",
                            FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                }
                else {

                ParseUser appUser = new ParseUser();
                appUser.setEmail(edtEmail.getText().toString());
                appUser.setUsername(edtUserName.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("signing up");
                progressDialog.show();
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {
                            FancyToast.makeText(SignUp.this, "Signup Successfull",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText(SignUp.this, "wrong credentials",
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                        }
                        progressDialog.dismiss();

                    }
                });
        }

                    break;
                    // WHEN LOGIN BUTTON IS TAPPED
            case R.id.btnLogin2:
                Intent i = new Intent(SignUp.this,Login.class);
                startActivity(i);
                break;
        }

    }
    // TO HIDE THE KEYBOARD WHEN EMPTY SCREEN IS TAPPED

    public void onViewTapped(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }
}
