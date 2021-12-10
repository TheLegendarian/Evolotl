package com.example.evolotl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private MainActivity mact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView title = (TextView) findViewById(R.id.TitleText);

        Button startbutton = (Button) findViewById(R.id.StartButton);
        // Searching axolotls https://axoltlapi.herokuapp.com/
        String url = new String("https://axoltlapi.herokuapp.com/");
        AsyncAxolotlJSONData task = new AsyncAxolotlJSONData(mact);
        try {
            JSONObject result = task.execute(url).get();
            String fact = result.getString("facts");
            TextView afact = (TextView) findViewById(R.id.AxolotlFact);
            afact.setText(fact);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        FeedReaderDbHelper mydb = new FeedReaderDbHelper(this);
        boolean is_inserted = mydb.insertData("George", "Blue", "0","axolotl_blue");
        if(is_inserted==true) {
            Toast.makeText(MainActivity.this, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(MainActivity.this, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }

        startbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent3 = new Intent(MainActivity.this, MainGame.class);
                MainActivity.this.startActivity(intent3);
            }
        });
    }
}