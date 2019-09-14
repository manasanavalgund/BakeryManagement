package com.example.manasa.bakery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.parse.ParseUser;

public class FinalActivity extends AppCompatActivity {
String quantity,rate;
    protected TextView display_textView;
    protected RatingBar ratingbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        display_textView=(TextView)findViewById(R.id.textView4);
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        display_textView.setText("Thank you for visiting Cake O'Clock!\nYour order will be delivered within 30 mins.\n\n\n\n\n\n\nYour feedback helps us improve our services.");

        Intent intent=getIntent();
        quantity=intent.getStringExtra("quantity");



            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                public void onRatingChanged(RatingBar ratingBar, float rating,
                                            boolean fromUser) {

                    rate = String.valueOf(rating);
                    ParseUser user = ParseUser.getCurrentUser();
                    user.put("rating", rate);
                    user.saveInBackground();


                }
            });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_final, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.logout)
        {
            ParseUser puser=ParseUser.getCurrentUser();
            puser.saveInBackground();
            ParseUser.logOut();
            Intent i=new Intent(this,p1.class);
            overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
