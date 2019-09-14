package com.example.manasa.bakery;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class DisplayMenuActivity extends Activity {
    ListView lv;
    protected List<ParseObject> mdisplay;
    protected TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_menu);

        lv = (ListView) findViewById(R.id.lv);
tv=(TextView)findViewById(R.id.tv);
        final Bundle i=getIntent().getExtras();
                final int case_var= i.getInt("position");
                ParseQuery<ParseObject> query = ParseQuery.getQuery("Products");
                switch(case_var) {

                    case 0:   query.whereStartsWith("p_id","C");
                                break;
                    case 1:    query.whereStartsWith("p_id","P");
                                break;
                    case 2:     query.whereStartsWith("p_id","K");
                                break;
                    case 3:     query.whereStartsWith("p_id","S");
                                break;
                    case 4:
                                query.whereStartsWith("p_id","D");
                                break;
                }
                query.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> display, ParseException e) {
                        if (e == null) {

                            mdisplay = display;
                            DisplayAdapter adapter = new DisplayAdapter(lv.getContext(), mdisplay);
                            lv.setAdapter(adapter);

                        }
                    }
                });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                ParseObject object=mdisplay.get(position);
                String objid=object.getObjectId();

                Intent intent=new Intent(DisplayMenuActivity.this,Detaildisplay.class);
                intent.putExtra("object id",objid);

                startActivity(intent);
                overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);

            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
}
