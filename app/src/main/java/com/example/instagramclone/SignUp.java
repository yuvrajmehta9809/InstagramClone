package com.example.instagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class SignUp extends AppCompatActivity {

    TextView tv;
    EditText editText,editName,editKIckSpeed;
    Button getData;
    String allKickBoxer = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.txtView);
        editText = findViewById(R.id.editText2);
        editName = findViewById(R.id.editName);
        editKIckSpeed = findViewById(R.id.editKickSpeed);
        getData = findViewById(R.id.getData);




        tv.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View view) {
                tv.setText("Hi World");

                try {
                    ParseObject kickBoxer = new ParseObject("KickBoxer");
                    kickBoxer.put("name",editName.getText().toString());
                    kickBoxer.put("kickspeed",Integer.parseInt(editKIckSpeed.getText().toString()));
                    kickBoxer.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){

                                FancyToast.makeText(SignUp.this,"saved",FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,true).show();
                            }
                        }
                    });


            }
                catch (Exception e){
                    // Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();
                    FancyToast.makeText(SignUp.this,e.getMessage(),
                            FancyToast.LENGTH_LONG,FancyToast.ERROR,true).show();

                }
        }



        });
        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery("KickBoxer");
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {

                            for (ParseObject parseObject : objects) {


                                allKickBoxer = allKickBoxer + parseObject.get("name") + "\n";
                            }

                        }
                    }
                });




            }
        });
    }


}
