package com.example.manasa.bakery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Detaildisplay extends AppCompatActivity implements View.OnClickListener {


    String quantity;
    String objectid;
    protected EditText qtytext;
    protected TextView pc,pn;
    protected Button buy_button;
    protected ImageView displayImageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildisplay);


        pn=(TextView)findViewById(R.id.pn);
        pc=(TextView)findViewById(R.id.pc);
        buy_button=(Button)findViewById(R.id.buy_button);
        qtytext=(EditText)findViewById(R.id.qtytext);



        Intent intent=getIntent();
        objectid=intent.getStringExtra("object id");


        ParseQuery<ParseObject> query=ParseQuery.getQuery("Products");
        query.whereEqualTo("objectId",objectid);
        query.getInBackground(objectid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                String p_name = parseObject.getString("p_name");
                Number p_cost = parseObject.getNumber("p_cost");

                pn.append(p_name);
                pc.append(""+p_cost);

                // displayTextview.setText("Name: " + p_name + "\nCost:" + p_cost);
                ParseFile p_image = (ParseFile) parseObject.getParseFile("p_image");


                p_image.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, ParseException e) {
                        if (e == null) {


                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            displayImageview = (ImageView) findViewById(R.id.iv1);
                            displayImageview.setImageBitmap(bmp);

                        }
                    }
                });



                // Picasso.with(getApplicationContext()).load().into(displayImageview);
            }
        });
            buy_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        quantity=qtytext.getText().toString();
        Intent intent=new Intent(Detaildisplay.this,LoginActivity.class);
        intent.putExtra("object id",objectid);
        intent.putExtra("quantity",quantity);
        startActivity(intent);
        overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detaildisplay, menu);
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
