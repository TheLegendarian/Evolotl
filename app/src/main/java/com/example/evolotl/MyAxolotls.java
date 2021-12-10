package com.example.evolotl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Context;
import android.widget.Toast;

public class MyAxolotls extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_axolotl_list);


        ListView list = (ListView)findViewById(R.id.listView1);
        ArrayAdapter<String> tableau = new ArrayAdapter<String>(list.getContext(), R.layout.support_simple_spinner_dropdown_item);

        FeedReaderDbHelper mydb = new FeedReaderDbHelper(this);

        
        //StringBuffer sb =

        mydb.readData();
        //AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //builder.setCancelable(true);
        //builder.setTitle("Data");
        //builder.setMessage(sb.toString());
        //builder.show();

        mydb.printData(tableau);
        mydb.close();
        list.setAdapter(tableau);
    }
}