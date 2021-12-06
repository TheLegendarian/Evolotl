package com.example.evolotl;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainGame extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Button listbutton = (Button) findViewById(R.id.ListButton);
        listbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent3 = new Intent(MainGame.this, MyAxolotls.class);
                MainGame.this.startActivity(intent3);
            }
        });

        ImageView eggid = (ImageView) findViewById(R.id.eggid);
        final int[] counter = {0};
        Toast mToastText = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        Button evolve_button = (Button) findViewById(R.id.EvolveButton);
        evolve_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter[0] += 1;
                int times_left= 50 - counter[0];
                if(counter[0]==50) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            evolve_button.setText("Feed");
                            eggid.setImageResource(R.drawable.axolotl_pink);
                        }
                    });
                }
                else {
                    mToastText.cancel();
                    mToastText.setText("Press " + times_left + " times to evolve! ");
                    mToastText.show();
                }

                Log.i("JFL"," counter =" + counter[0]);
            }
        });
    }
}
