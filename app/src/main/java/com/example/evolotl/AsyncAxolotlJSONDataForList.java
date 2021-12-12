package com.example.evolotl;

import android.os.AsyncTask;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class AsyncAxolotlJSONDataForList extends AsyncTask<String, Void, ArrayList<String>> {
    private AppCompatActivity myActivity;
    private MyAdapter adapter;

    public AsyncAxolotlJSONDataForList(MyAdapter adapter, AppCompatActivity myActivity_) {
        this.adapter = adapter;
        myActivity = myActivity_;
    }

    protected ArrayList<String> doInBackground(String... strings) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        ArrayList<String> urls=new ArrayList<String>();
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); //open
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                urls.add(inputLine);
            }
            Log.i("JFL","input " + urls);
            in.close();
            urlConnection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("JFL", "Returning links");
        return urls; // returns the result
    }

    protected void onPostExecute(ArrayList<String> a_s) { //TODO change url to have the array of urls, check if JSON
        Log.i("JFL","data: " + a_s);

        if (a_s != null) {
            Log.i("JFL", "String data: " + a_s);
            for (int i = 0; i < a_s.size(); i++) {
                String image_url = (String) a_s.get(i);
                Log.i("JFL", "Adding to adapter url : " + image_url);
                adapter.add(image_url);
            }
            adapter.notifyDataSetChanged();
        }
else
        {
            Log.i("JFL", "The JSON data are null");
        }

    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while (i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }
}
