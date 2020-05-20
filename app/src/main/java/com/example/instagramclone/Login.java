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

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.shashank.sony.fancytoastlib.FancyToast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    //DECLARING UI COMPONENTS.

    Button btnLogin,btnSignUp;
    EditText edtUserName,edtPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");

        //INITIALISING UI COMPONENTS.

        btnLogin = findViewById(R.id.btnLogin2);
        btnSignUp = findViewById(R.id.btnSignUp2);
        edtUserName = findViewById(R.id.edtUserNameLogin);
        edtPassword = findViewById(R.id.edtPasswordLogin);

        // USING ENTER KEY ON KEYBOARD TO LOGIN THE USER.

        edtPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {

                if(keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

                    onClick(btnLogin);
                }
                return false;
            }
        });

        //DECLARING ON CLICK LISTENER FOR BUTTONS.

        btnLogin.setOnClickListener(Login.this);
        btnSignUp.setOnClickListener(Login.this);
        if(ParseUser.getCurrentUser() != null){

            switchActivity();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnLogin2:

                if (edtUserName.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")) {

                    FancyToast.makeText(Login.this, "username,password are required",
                            FancyToast.LENGTH_LONG, FancyToast.INFO, true).show();
                }
                else {
                   final ProgressDialog  progressDialog = new ProgressDialog(this);
                    progressDialog.setMessage("please wait");
                    progressDialog.show();
                ParseUser.logInInBackground(edtUserName.getText().toString(), edtPassword.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null && user != null) {
                                    FancyToast.makeText(Login.this, "Login Successfull",
                                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                                    switchActivity();
                                } else {
                                    FancyToast.makeText(Login.this, "wrong credentials",
                                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();


                                }
                                progressDialog.dismiss();
                            }



                        });
        }
                break;

            case R.id.btnSignUp2 :
                Intent i = new Intent(Login.this,SignUp.class);
                startActivity(i);
                break;
        }

    }
    //TO HIDE THE KEYBOARD WHEN EMPTY SCREEN IS TAPPED.
    public void onViewTapped(View view){

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }

    public void switchActivity(){

        Intent i = new Intent(Login.this,SocialMediaActivity.class);
        startActivity(i);
    }
}
