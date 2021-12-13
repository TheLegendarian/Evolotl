package com.example.evolotl;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

        Button imlistbutton = (Button) findViewById(R.id.ShowButton);
        imlistbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("JFL", "changing page");
                Intent intent3 = new Intent(MainGame.this, ListActivity.class);
                MainGame.this.startActivity(intent3);
            }
        });



        //INITIALISATION LAYOUT
        TextView aname = (TextView) findViewById(R.id.a_name);
        TextView hap_display = (TextView) findViewById(R.id.happiness);
        ImageView eggid = (ImageView) findViewById(R.id.eggid);
        Button evolve_button = (Button) findViewById(R.id.EvolveButton);
        Toast mToastText = Toast.makeText(this, "", Toast.LENGTH_SHORT);

        //INITIALISATION DATABASE
        final GlobalHappiness gHappiness = (GlobalHappiness) getApplicationContext();
        Integer id_a= gHappiness.getIdCurr();
        FeedReaderDbHelper mydb = new FeedReaderDbHelper(this);
        String name = mydb.printName(id_a);
        final Integer[] lvl = {mydb.printLevel(id_a)};
        String img_name = mydb.printImage(id_a);
        mydb.close();
        aname.setText(name);
        eggid.setImageResource(getResources().getIdentifier(img_name, "drawable", getPackageName()));

        //BOUTON RENOMMAGE
        Button rename_axolotl = (Button) findViewById(R.id.RenameButton);

        final EditText renametxt = new EditText(this);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        rename_axolotl.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                alert.setMessage("Rename your Axolotl:");
                alert.setTitle("Rename");
                alert.setView(renametxt);
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mydb.updateName(renametxt.getText().toString(), id_a);
                        aname.setText(mydb.printName(id_a));
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                alert.show();
            }
        });


        //Egg setup if level is 0
        if(lvl[0] ==0) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    evolve_button.setText("Hatch");
                    eggid.setImageResource(R.drawable.egg_test);
                }
            });
        }

        final int[] counter = {0};
        evolve_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter[0] += 1;
                int times_left= 20 - counter[0];
                //Niveau OEUF
                if(lvl[0] ==0) {
                    if (counter[0] == 20) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                evolve_button.setText("Feed");
                                eggid.setImageResource(getResources().getIdentifier(img_name, "drawable", getPackageName()));
                                mydb.updateData(lvl[0] +1,id_a);
                                lvl[0] = mydb.printLevel(id_a);
                                counter[0] = 0;
                            }
                        });
                    } else {
                        mToastText.cancel();
                        mToastText.setText("Press " + times_left + " times to hatch! ");
                        mToastText.show();
                    }
                }
                //Niveau AXOLOTL
                else {
                    int happiness = gHappiness.getHappiness();
                    happiness+= id_a;
                    gHappiness.setHappiness(happiness);
                    hap_display.setText(" " + happiness + " ");
                    mToastText.cancel();
                    mToastText.setText("You have " + happiness + " happiness! ");
                    mToastText.show();
                }

                Log.i("JFL"," counter =" + counter[0]);
            }
        });
    }
}
