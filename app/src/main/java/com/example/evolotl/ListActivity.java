package com.example.evolotl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //ImageView axolotl = (ImageView) findViewById(R.id.axolotl);
        //Button b1 = (Button)findViewById(R.id.loadmore2);
        //b1.setOnClickListener(new GetImageOnClickListener(adapter,ListActivity.this));
        String url = new String("https://raw.githubusercontent.com/AxolotlAPI/data/main/pictures.txt");
        AsyncAxolotlJSONDataForList task2 = new AsyncAxolotlJSONDataForList(this);
        AsyncBitmapDownloader img = new AsyncBitmapDownloader(this);
        int n = 5;
        ArrayList<String> in = new ArrayList<>();

        try {
            ArrayList<String> result = task2.execute(url).get();
            for(int i = 0; i < (n); i++) {
                in.add(result.get(i));
            }
            ImageAdapter adapter2 = new ImageAdapter(this, this, R.layout.textviewlayout, in);
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