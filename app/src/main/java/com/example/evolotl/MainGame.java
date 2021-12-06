package com.example.evolotl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }
}
