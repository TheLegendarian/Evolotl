package com.example.evolotl;

import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class GetImageOnClickListener implements View.OnClickListener {
    private Axolotl_images aximg;

    public GetImageOnClickListener(Axolotl_images aximg) {
        this.aximg = aximg;
    }

    @Override
    public void onClick(View v) {

        // Searching axolotls https://axoltlapi.herokuapp.com/
        String url = new String("https://axoltlapi.herokuapp.com/");
        AsyncAxolotlJSONData task = new AsyncAxolotlJSONData(aximg);
        AsyncBitmapDownloader img = new AsyncBitmapDownloader(aximg);
        try {
            JSONObject result = task.execute(url).get();
            String image_url = result.getString("url");
            img.execute(image_url);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}