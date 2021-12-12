package com.example.evolotl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAxolotls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axolotl_list);

        Log.i("JFL","oncrate started list");
        ListView list = (ListView)findViewById(R.id.listView1);


        FeedReaderDbHelper mydb = new FeedReaderDbHelper(this);
        mydb.readData();
        ArrayList<Axolotl> axlist = mydb.printData(this);
        AxolotlListAdapter adapter = new AxolotlListAdapter(this, R.layout.adapter_view_layout, axlist);
        mydb.close();
        list.setAdapter(adapter);
    }
}