package com.example.manasa.bakery;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;

public class p1 extends Activity {

    ListView lv1;
    TextView tv;
    int[] images = {R.drawable.i12, R.drawable.i13, R.drawable.i14, R.drawable.i15, R.drawable.i16};
    String[] menu_items;
    MenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_p1);

        tv = (TextView) findViewById(R.id.tv);
        lv1 = (ListView) findViewById(R.id.lv1);
        menu_items = getResources().getStringArray(R.array.Menu);
        int i = 0;
        adapter = new MenuAdapter(getApplicationContext(), R.layout.layout_1);
        lv1.setAdapter(adapter);


        for (String items : menu_items)
        {
            MenuData data = new MenuData(images[i], items);
            adapter.add(data);
            i++;
        }


        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Intent i = new Intent(p1.this,DisplayMenuActivity.class);
                i.putExtra("position",position);
                startActivity(i);
                overridePendingTransition(R.animator.animate_up, R.animator.slide_up2);
            }
        });
    }

}


