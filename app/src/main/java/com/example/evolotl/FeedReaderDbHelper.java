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
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 13;
    private static final String DATABASE_FILE_NAME = "Mydatabase";
    private static final String DATABASE_TABLE_NAME = "mydatabase";
    private static final String PKEY = "ID";
    private static final String COL1 = "NAME";
    private static final String COL2 = "COLOR";
    private static final String COL3 = "LEVEL";
    private static final String COLIMG = "IMAGE_NAME";
    private static final String COLPRICE = "PRICE";
    private static final String COLOBTAINED = "OBTAINED";
    private final Context mContext;

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_TABLE_CREATE = "CREATE TABLE " + DATABASE_TABLE_NAME + " (" +
                PKEY + " INTEGER PRIMARY KEY," +
                COL1 + " TEXT," +
                COL2 + " TEXT," +
                COL3 + " INTEGER," +
                COLIMG + " TEXT," +
                COLPRICE + " INTEGER," +
                COLOBTAINED + " INTEGER);";
        db.execSQL(DATABASE_TABLE_CREATE);
        FeedReaderDbHelper mydb = new FeedReaderDbHelper(mContext);

        boolean is_inserted = mydb.insertData(db,"Pink Axolotl", "pink", "0","axolotl_pink",0,1);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }

        is_inserted = mydb.insertData(db,"White Axolotl", "white", "0","axolotl_white",100,0);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }

        is_inserted = mydb.insertData(db,"Brown Axolotl", "Brown", "0","axolotl_brown",200,0);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }
        is_inserted = mydb.insertData(db,"Blue Axolotl", "Blue", "0","axolotl_blue",400,0);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }
        is_inserted = mydb.insertData(db,"Gold Axolotl", "Golden", "0","axolotl_gold",1000,0);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }
        is_inserted = mydb.insertData(db,"Legendary Rainbow Axolotl", "Rainbow", "0","axolotl_rainbow",10000,0);
        if(is_inserted==true) {
            Toast.makeText(mContext, "Axolotl Adopted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(mContext, "Axolotl Fled", Toast.LENGTH_SHORT).show();
        }
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
    public boolean insertData(SQLiteDatabase db, String name, String color, String level, String img_name, Integer price, Integer obtained) {
        Log.i("JFL"," Insert in database");

        ContentValues values = new ContentValues();
        values.put(COL1,name);
        values.put(COL2,color);
        values.put(COL3,level);
        values.put(COLIMG,img_name);
        values.put(COLPRICE,price);
        values.put(COLOBTAINED,obtained);
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
        String strSQL = "UPDATE "+ DATABASE_TABLE_NAME+" SET " + COL3 +" = " + lvl + " WHERE " + PKEY+ " = "+ id_a;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(strSQL);
    }

    public void updateName(String newname, Integer id_a) {
        //String strSQL = "UPDATE "+ DATABASE_TABLE_NAME+" SET " + COL1 + " = " + newname + " WHERE " + PKEY+ " = "+ id_a;
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(COL1, newname);
        db.update(DATABASE_TABLE_NAME, val, PKEY + " = '" + id_a + "'", null);
        //db.execSQL(strSQL);
    }
    public void updateObtained(Integer id_a) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues val = new ContentValues();
        val.put(COLOBTAINED, 1);
        db.update(DATABASE_TABLE_NAME, val, PKEY + " = '" + id_a + "'", null);
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
    public ArrayList<Axolotl> printData(Context context) { //TODO en faire une classe
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        String name = "";
        Integer id =0;
        ArrayList<Axolotl> custArr = new ArrayList<Axolotl>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                name=(cursor.getString(cursor.getColumnIndex(COL1)));
                id=(cursor.getInt(cursor.getColumnIndex(PKEY)));
                custArr.add(new Axolotl(name,id));
            } while (cursor.moveToNext());
            cursor.close();
            return custArr;
        }
        return null;
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

    @SuppressLint("Range")
    public String printColor(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getString(cursor.getColumnIndex(COL2));
    }

    @SuppressLint("Range")
    public Integer printPrice(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COLPRICE));
    }

    @SuppressLint("Range")
    public Integer printObtained(int nb) {
        String select = new String("SELECT * from " + DATABASE_TABLE_NAME + " WHERE " + PKEY + "=" + nb);
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(select, null);
        cursor.moveToFirst();
        return cursor.getInt(cursor.getColumnIndex(COLOBTAINED));
    }
}