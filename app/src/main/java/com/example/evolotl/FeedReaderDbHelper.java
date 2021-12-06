package com.example.evolotl;

import static com.example.evolotl.FeedReaderContract.SQL_DELETE_ENTRIES;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 3;
    private static final String DATABASE_FILE_NAME = "Mydatabase";
    private static final String DATABASE_TABLE_NAME = "mydatabase";
    private static final String PKEY = "ID";
    private static final String COL1 = "NAME";
    private static final String COL2 = "COLOR";
    private static final String COL3 = "LEVEL";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY," +
                COL1 + " TEXT," +
                COL2 + " TEXT," +
                COL3 + " INTEGER);";
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
    public boolean insertData(String name, String color, String level) {
        Log.i("JFL"," Insert in database");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL1,name);
        values.put(COL2,color);
        values.put(COL3,level);
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

    @SuppressLint("Range")
    public void readData() {
        Log.i("JFL", "Reading database...");
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        Log.i("JFL", "Number of entries: " + cursor.getCount());
        if (cursor.getCount() > 0) {
            //StringBuffer buffer = new StringBuffer();
            cursor.moveToFirst();
            do {
                //buffer.append("Id :" + cursor.getString(0) + "\n");
                //buffer.append("Name :" + cursor.getString(1) + "\n");
                //buffer.append("Color :" + cursor.getString(2) + "\n");
                //buffer.append("Level :" + cursor.getString(3) + "\n\n");
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(COL1)));
                Log.i("JFL", "Reading: " + cursor.getString(cursor.getColumnIndex(COL2)));
            } while (cursor.moveToNext());
        }
        db.close();
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

}