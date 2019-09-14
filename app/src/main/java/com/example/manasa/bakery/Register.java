package com.example.manasa.bakery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Register extends Activity {

    protected EditText mUsername;
    protected EditText mEmail;
    protected EditText mPassword;
    protected Button mRegisterButton;
    protected EditText mphone;
String objectid,quantity;
protected EditText maddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUsername=(EditText)findViewById(R.id.UsernameEditText);
        mPassword=(EditText)findViewById(R.id.PasswordEditText);
        mEmail=(EditText)findViewById(R.id.EmailEdittext);
        mRegisterButton=(Button)findViewById(R.id.SignupButton);
        maddress=(EditText)findViewById(R.id.addressEdittext);
        mphone=(EditText)findViewById(R.id.phoneEditText);


        Intent intent=getIntent();
        objectid=intent.getStringExtra("object id");
        quantity=intent.getStringExtra("quantity");


        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get username,password and email in string format

                String username=mUsername.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String email=mEmail.getText().toString().trim();
                String address =maddress.getText().toString().trim();
                String phone_no=mphone.getText().toString().trim();



                //store in parse
                ParseUser user = new ParseUser();
                user.setUsername(username);
                user.setEmail(email);
                user.setPassword(password);
                user.put("address", address);
                user.put("phone_no",phone_no);


                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(Register.this, "Sign up successful", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Register.this, LoginActivity.class);
                            i.putExtra("object id", objectid);
                            i.putExtra("quantity",quantity);
                            startActivity(i);
                            overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
                        }

                        else {
                            //There is an exception. Advice user.
                        }
                    }
                });

            }
        });
    }

}





