package com.example.evolotl;

import static com.example.evolotl.FeedReaderContract.SQL_DELETE_ENTRIES;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 8;
    private static final String DATABASE_FILE_NAME = "Mydatabase";
    private static final String DATABASE_TABLE_NAME = "mydatabase";
    private static final String PKEY = "ID";
    private static final String COL1 = "NAME";
    private static final String COL2 = "COLOR";
    private static final String COL3 = "LEVEL";
    private static final String COLIMG = "IMAGE_NAME";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY," +
                COL1 + " TEXT," +
                COL2 + " TEXT," +
                COL3 + " INTEGER," +
                COLIMG + " TEXT);";
        db.execSQL(DATABASE_TABLE_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public boolean insertData(String name, String color, String level, String img_name) {
        Log.i("JFL"," Insert in database");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1,name);
        values.put(COL2,color);
        values.put(COL3,level);
        values.put(COLIMG,img_name);
        Log.i("JFL", "Inserting values");
        long result = db.insert(DATABASE_TABLE_NAME,null, values);
        if(result == -1 ) {
            return false;
        }
        else {
            return true;
        }
    }

    public void clearDatabase(String TABLE_NAME) {
        String clearDBQuery = "DELETE FROM "+TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(clearDBQuery);
    }

    public void updateData(Integer lvl, Integer id_a) {
        String strSQL = "UPDATE "+ DATABASE_TABLE_NAME+" SET " + COL3 +" = " + lvl+ " WHERE " + PKEY+ " = "+ id_a;
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL(strSQL);
    }

    @SuppressLint("Range")
    public void readData() {
        Log.i("JFL", "Reading database...");
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(COL1)));
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(COL2)));
            } while (cursor.moveToNext());
        }
        db.close();
    }
    @SuppressLint("Range")
    private static class
    ViewHolder {
        TextView col_1;
        TextView col_2;
        TextView col_3;
        TextView col_img;
    }

    @SuppressLint("Range")
    public void printData(ArrayAdapter<String> tableau) { //TODO faire tableau a plusieurs colonnes
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                tableau.add(cursor.getString(cursor.getColumnIndex(COL1)));
                tableau.add(cursor.getString(cursor.getColumnIndex(COL2)));
                tableau.add(cursor.getString(cursor.getColumnIndex(COL3)));
            } while (cursor.moveToNext());
        }
    }

    @SuppressLint("Range")
    public String printName(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL1));
    }

    @SuppressLint("Range")
    public Integer printLevel(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COL3));
    }

    @SuppressLint("Range")
    public String printImage(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COLIMG));
    }
}