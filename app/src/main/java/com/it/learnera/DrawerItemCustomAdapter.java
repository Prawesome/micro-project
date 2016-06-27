package com.it.learnera;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Prejith on 6/27/2016.
 */

public class DrawerItemCustomAdapter extends ArrayAdapter<ObjectDrawerItem> {

    ObjectDrawerItem data[] = null;
    Context mContext;
    int layoutResourceId;

    public DrawerItemCustomAdapter(Context mContext, int layoutResourceId, ObjectDrawerItem[] data) {
        super(mContext, layoutResourceId, data);

        this.mContext = mContext;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
        listItem = inflater.inflate(layoutResourceId, parent, false);

        TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        ObjectDrawerItem folder = data[position];

        textViewName.setText(folder.name);

        return listItem;
    }
}
