package com.example.manasa.bakery;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.parse.Parse;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.enableLocalDatastore(this);
        //Parse.initialize(this, "R93vd5B8w86u4SILpOK7Y6O1vIoCua9EPrPXA7gM", "db7sKk5DKWnQm1yBkHOxtlcJtsL3TMuq8M4hK6Ws");
       Parse.initialize(this, "0mqYaZycyJtB0iRwkRUYugFS5H6avYNwqvflTmRT", "pgLlc1RrIDbgZiLINWlNnTlOjHQ5y4Ti3azqQDPK");
        //Parse.initialize(this, "R93vd5B8w86u4SILpOK7Y6O1vIoCua9EPrPXA7gM", "db7sKk5DKWnQm1yBkHOxtlcJtsL3TMuq8M4hK6Ws");
        tv1=(TextView)findViewById(R.id.tv1);
        tv1.setPaintFlags(tv1.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tv1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this,p1.class));

        overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
    }
}
