package com.example.evolotl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //ImageView axolotl = (ImageView) findViewById(R.id.axolotl);
        //Button b1 = (Button)findViewById(R.id.loadmore2);
        MyAdapter adapter = new MyAdapter();
        //b1.setOnClickListener(new GetImageOnClickListener(adapter,ListActivity.this));
        String url = new String("https://raw.githubusercontent.com/AxolotlAPI/data/main/pictures.txt");
        AsyncAxolotlJSONDataForList task2 = new AsyncAxolotlJSONDataForList(adapter,this);
        AsyncBitmapDownloader img = new AsyncBitmapDownloader(this);
        try {
            ArrayList<String> result = task2.execute(url).get();
            ImageAdapter adapter2 = new ImageAdapter(this, R.layout.textviewlayout, result);
            Log.i("JFL", "searching images");
            ListView l = (ListView)findViewById(R.id.listView2);
            l.setAdapter(adapter2);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}