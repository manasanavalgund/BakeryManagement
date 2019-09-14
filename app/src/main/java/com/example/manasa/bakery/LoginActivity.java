package com.example.manasa.bakery;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends Activity implements View.OnClickListener {

    protected EditText musername;
    protected EditText mpassword;
    protected Button mLoginButton;
    protected Button mCreateAccountButton;
    protected TextView tv;
    String objectid;
    String quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialise edittext and buttons
        musername=(EditText)findViewById(R.id.UsernameLoginEditText);
        mpassword=(EditText)findViewById(R.id.PasswordLoginEditText);
        mLoginButton=(Button)findViewById(R.id.LoginButton);
        mCreateAccountButton=(Button)findViewById(R.id.CreateAccountButton);
        tv=(TextView)findViewById(R.id.tvtp);



        Intent intent=getIntent();
        objectid=intent.getStringExtra("object id");
        quantity=intent.getStringExtra("quantity");

        tv.setText(quantity);

        mLoginButton.setOnClickListener(this);
        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.LoginButton:
            {
                final String username=musername.getText().toString().trim();
                String password=mpassword.getText().toString().trim();

                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser parseUser, ParseException e) {

                        if(e==null)
                        {
                            //Login successful
                            Toast.makeText(LoginActivity.this,"Welcome "+username,Toast.LENGTH_LONG).show();
                            Intent i=new Intent(LoginActivity.this,confirmation_activity.class);
                            i.putExtra("object id",objectid);
                            i.putExtra("quantity", quantity);
                            startActivity(i);
                            overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
                        }
                        else
                        {
                            AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage(e.getMessage());
                            builder.setTitle("Sorry!");
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog dialog=builder.create();
                            dialog.show();
                        }

                    }


                });
            }
            break;
            case R.id.CreateAccountButton:
            {
                Intent i=new Intent(LoginActivity.this,Register.class);
                i.putExtra("object id",objectid);
                i.putExtra("quantity",quantity);
                startActivity(i);
                overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
            }
            break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
