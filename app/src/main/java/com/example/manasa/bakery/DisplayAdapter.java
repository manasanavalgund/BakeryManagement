package com.example.manasa.bakery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.squareup.picasso.Picasso;

import java.sql.Blob;
import java.util.List;

/**
 * Created by manasa on 27/9/15.
 */
public class DisplayAdapter extends ArrayAdapter<ParseObject> {

    protected Context mcontext;
    protected List<ParseObject> mdisplay;

    public DisplayAdapter(Context context,List<ParseObject> display)
    {
            super(context,R.layout.layout_2,display);
            mcontext=context;
            mdisplay=display;

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Viewholder holder;

        if(convertView==null)
        {
            convertView= LayoutInflater.from(mcontext).inflate(R.layout.layout_2,null);
            holder=new Viewholder();
            holder.dp_name=(TextView)convertView.findViewById(R.id.p_name);
            holder.dp_cost=(TextView)convertView.findViewById(R.id.p_cost);
            holder.dp_image=(ImageView)convertView.findViewById(R.id.p_image);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Viewholder)convertView.getTag();
        }


        ParseObject displayObject=mdisplay.get(position);
        Picasso.with(getContext()).load(displayObject.getParseFile("p_image").getUrl()).placeholder(R.drawable.icon).into(holder.dp_image);

        String name=displayObject.getString("p_name");
        holder.dp_name.setText(name);

        Number cost=displayObject.getNumber("p_cost");
        holder.dp_cost.setText(""+cost);





            return convertView;
    }

    public static class Viewholder
    {
        TextView dp_name;
        TextView dp_cost;
        ImageView dp_image;
    }

}
