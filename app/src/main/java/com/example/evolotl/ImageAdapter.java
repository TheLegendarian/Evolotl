package com.example.evolotl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

public class ImageAdapter extends ArrayAdapter<String> {
    int mResource;
    private Context mContext;
    private AppCompatActivity myActivity;

    public ImageAdapter(AppCompatActivity activity, Context context, int resource, ArrayList<String> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;
        myActivity = activity;
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
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imView);
        String url = getItem(position).toString();
        AsyncBitmapDownloader img = new AsyncBitmapDownloader(myActivity);
        try {
            imageView.setImageBitmap(img.execute(url).get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return convertView;
    }
}


