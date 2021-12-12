package com.example.evolotl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<String> {
    int mResource;
    private Context mContext;

    public ImageAdapter(Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;
    }

    @SuppressLint({"SetTextI18n", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        //INITIALISATION VARIABLES
        Toast mToastText = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        //INITIALISATIONS VARIABLES AXOLOTL
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        //INITIALISATION TEXTVIEWS
        TextView tv= (TextView) convertView.findViewById(R.id.tv);
        String url = getItem(position).toString();
        Log.i("JFL", "url :" + url);
        tv.setText(url);

        return convertView;
    }
}


