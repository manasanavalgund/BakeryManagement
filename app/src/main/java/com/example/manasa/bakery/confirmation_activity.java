package com.example.manasa.bakery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class confirmation_activity extends AppCompatActivity implements View.OnClickListener{
    String objectid,qty1,quantity,id;
    protected TextView prod,quant,tcost;
    protected ImageView confirm_image;
    protected Button confirm_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_activity);

        prod=(TextView)findViewById(R.id.prod);
        quant=(TextView)findViewById(R.id.quant);
        tcost=(TextView)findViewById(R.id.tcost);

        Button confirm_button=(Button)findViewById(R.id.confirm_button);

        Intent intent=getIntent();
        objectid=intent.getStringExtra("object id");
        quantity=intent.getStringExtra("quantity");


        ParseUser currentuser=ParseUser.getCurrentUser();
        currentuser.put("qty", quantity);
        currentuser.saveInBackground();
        id=currentuser.getObjectId();

        if(currentuser!=null)
        {
            qty1=currentuser.getString("qty");
        }



        ParseQuery<ParseObject> query=ParseQuery.getQuery("Products");
        query.whereEqualTo("objectId", objectid);
        query.getInBackground(objectid, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {


                String p_name = parseObject.getString("p_name");
                int p_cost = parseObject.getInt("p_cost");
                int p_qty =Integer.parseInt(qty1);

                //confirm_text.setText("Name: " + p_name + "\nCost:" + p_cost+ "\nQuantity:" + p_qty + "\nYour total bill is " + (p_cost) + " (including tax and delivery charges)");
                prod.append(p_name);
                quant.append(""+p_qty);
                tcost.append(""+p_cost*p_qty);


                ParseFile p_image = (ParseFile) parseObject.getParseFile("p_image");
                p_image.getDataInBackground(new GetDataCallback() {
                    @Override
                    public void done(byte[] bytes, ParseException e) {
                        if (e == null) {
                            Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            confirm_image = (ImageView) findViewById(R.id.iv1);
                            confirm_image.setImageBitmap(bmp);
                        }
                    }
                });

            }
        });




        confirm_button.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirmation_activity, menu);
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

    @Override
    public void onClick(View v) {

        ParseObject billobject=new ParseObject("Bill");

        billobject.put("u_id",id);
        billobject.put("p_oid",objectid);
        billobject.put("qty",qty1);
        billobject.saveInBackground();
        Toast.makeText(confirmation_activity.this, "Order confirmed!", Toast.LENGTH_LONG).show();
        Intent i=new Intent(confirmation_activity.this,FinalActivity.class);
        startActivity(i);
        overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
    }
}
