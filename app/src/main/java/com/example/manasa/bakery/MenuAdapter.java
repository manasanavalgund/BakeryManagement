package com.example.manasa.bakery;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manasa on 12/9/15.
 */
public class MenuAdapter extends ArrayAdapter{

        List list=new ArrayList();

    public MenuAdapter(Context context,int resource)

    {
            super(context,resource);

    }

    @Override
    public void add(Object object)
    {

        super.add(object);
        list.add(object);
    }



    public void setList(List list) {
        this.list = list;
    }

    static class Datahandler
    {
        TextView tv;
        ImageView iv;

    }

    @Override
    public int getCount()
    {
        return this.list.size();
    }


    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        row=convertView;
        Datahandler handler;
        if(convertView==null)
        {
            LayoutInflater inflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.layout_1,parent,false);
            handler=new Datahandler();
            handler.iv=(ImageView)row.findViewById(R.id.iv1);
            handler.tv=(TextView)row.findViewById(R.id.tv2);
                    row.setTag(handler);

        }

        else
        {
            handler=(Datahandler)row.getTag();
        }

        MenuData data;
        data=(MenuData)this.getItem(position);
        handler.iv.setImageResource(data.getImages());
        handler.tv.setText(data.getMenu_items());

        return row;
    }
}
