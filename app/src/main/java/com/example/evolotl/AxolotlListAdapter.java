package com.example.evolotl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class AxolotlListAdapter  extends ArrayAdapter<Axolotl> {
    int mResource;
    private Context mContext;

    public AxolotlListAdapter(Context context, int resource, ArrayList<Axolotl> objects) {
        super(context, resource, objects);
        mContext= context;
        mResource = resource;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedReaderDbHelper mydb = new FeedReaderDbHelper(mContext);


        //INITIALISATION VARIABLES
        //final GlobalHappiness gHappiness = (GlobalHappiness) mContext.getApplicationContext();
        //final int[] happiness = {gHappiness.getHappiness()};
        //final int[] idCurr = {gHappiness.getIdCurr()};
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final int[] happiness = {preferences.getInt("happiness", 0)};
        final int idCurr = preferences.getInt("idCurr", 0);
        Toast mToastText = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        //INITIALISATIONS VARIABLES AXOLOTL
        Integer id =getItem(position).getId();
        String name=getItem(position).getName();
        Axolotl ax = new Axolotl(name,id);
        Integer lvl = mydb.printLevel(id);
        String color = mydb.printColor(id);
        Integer price = mydb.printPrice(id);
        Integer obtained = mydb.printObtained(id);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        //INITIALISATION TEXTVIEWS
        TextView tvName= (TextView) convertView.findViewById(R.id.textView1);
        TextView tvColor= (TextView) convertView.findViewById(R.id.textView2);
        TextView tvLvl= (TextView) convertView.findViewById(R.id.textView3);
        Button buybtn = (Button) convertView.findViewById(R.id.BuyButton);


        if(obtained == 1) {
            buybtn.setText("Select");
        }
        else {
            buybtn.setText("Buy : " + price);
        }
        buybtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(obtained == 0) {
                    if(happiness[0] >= price) {
                        happiness[0] = happiness[0] - price;
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("happiness", happiness[0]); // value to store
                        editor.commit();
                        mydb.updateObtained(id);
                        mToastText.cancel();
                        mToastText.setText("Axolotl Bought! ");
                        mToastText.show();
                        Intent intent3 = new Intent(mContext, MyAxolotls.class);
                        mContext.startActivity(intent3);
                    }
                    else {
                        mToastText.cancel();
                        mToastText.setText("You have need more happiness! ");
                        mToastText.show();
                    }
                }
                else {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("idCurr", id); // value to store
                    editor.commit();
                    mToastText.cancel();
                    mToastText.setText("Axolotl Selected ");
                    mToastText.show();
                    Intent intent3 = new Intent(mContext, MainGame.class);
                    mContext.startActivity(intent3);
                }
            }
        });
        tvName.setText(name);
        tvColor.setText(color);
        tvLvl.setText(lvl.toString());

        return convertView;
    }
}


