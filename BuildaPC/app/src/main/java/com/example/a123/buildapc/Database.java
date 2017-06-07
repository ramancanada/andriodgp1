package com.example.a123.buildapc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 6/5/2017.
 */

public class Database extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "dataManager";

    // Contacts table name
    private static final String TABLE_DATAS = "datas";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_ITEM = "item";
    private static final String KEY_COMMENT = "comment";
    private static final String KEY_DATE = "date";
    private static final String KEY_TIME = "time";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_DATAS_TABLE = "CREATE TABLE " + TABLE_DATAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM + " TEXT,"
                + KEY_COMMENT + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_DATAS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATAS);

        // Create tables again
        onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    void addData(Datas data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEM, data.getItem()); // Contact Name
        values.put(KEY_COMMENT, data.getComment()); // Contact Phone
        values.put(KEY_DATE, data.getDate()); // Contact Name
        values.put(KEY_TIME, data.getTime()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_DATAS, null, values);
        db.close(); // Closing database connection
    }

    // Getting single contact
    Datas getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATAS, new String[] { KEY_ID,
                        KEY_ITEM, KEY_COMMENT, KEY_DATE, KEY_TIME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Datas data = new Datas(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4));
        // return contact
        return data;
    }

    Datas getItemOnly(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DATAS, new String[] { KEY_ID,
                        KEY_ITEM, KEY_COMMENT, KEY_DATE, KEY_TIME}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Datas data = new Datas(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3), cursor.getString(4));
        // return contact
        return data;
    }

    // Getting All Contacts
    public List<Datas> getAllDatas() {
        List<Datas> dataList = new ArrayList<Datas>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DATAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Datas data = new Datas();
                data.setID(Integer.parseInt(cursor.getString(0)));
                data.setItem(cursor.getString(1));
                data.setComment(cursor.getString(2));
                data.setDate(cursor.getString(3));
                data.setTime(cursor.getString(4));
                // Adding contact to list
                dataList.add(data);
            } while (cursor.moveToNext());
        }

        // return contact list
        return dataList;
    }

    // Updating single contact
    public void updateDatas(int position, String value) {
        SQLiteDatabase db = this.getWritableDatabase();

        String update = "UPDATE datas SET comment = '"+ value +"' WHERE ID = " + position;
        db.execSQL(update);

    }

    // Deleting single contact
    public void deleteValues(int position) {
        SQLiteDatabase db = this.getWritableDatabase();
        position = position + 1;
        String id = String.valueOf(position);
        db.delete(TABLE_DATAS, KEY_ID + "="+id,null);
        //Updating table
        //Creating temporary table

        String CREATE_TABLE_COPY = "CREATE TABLE " + "COPIED_TABLE" + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM + " TEXT,"
                + KEY_COMMENT + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT" + ")";
        db.execSQL(CREATE_TABLE_COPY);
        // Copying necessary columns to new temporary table
        String db_insert_command;
        db_insert_command = "INSERT INTO COPIED_TABLE (" + KEY_ITEM +", " + KEY_COMMENT + ", " + KEY_DATE + ", " + KEY_TIME + ") SELECT " + KEY_ITEM +", " + KEY_COMMENT + ", " + KEY_DATE + ", " + KEY_TIME + " FROM "+ TABLE_DATAS;
        System.out.println(db_insert_command);
        db.execSQL(db_insert_command);
        //Dropping old table
        db.execSQL("DROP TABLE " + TABLE_DATAS);
        //Creating old table again
        String CREATE_TABLE = "CREATE TABLE " + TABLE_DATAS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEM + " TEXT,"
                + KEY_COMMENT + " TEXT," + KEY_DATE + " TEXT," + KEY_TIME + " TEXT"  + ")";
        db.execSQL(CREATE_TABLE);
        //Copying all fields from temporary table to newly created old table
        db.execSQL("INSERT INTO " + TABLE_DATAS + " SELECT * FROM COPIED_TABLE");
        //Dropping temporary table
        db.execSQL("DROP TABLE COPIED_TABLE");
        db.close();
    }


    // Getting contacts Count
    public int getDatasCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DATAS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();

        // return count
        return cursor.getCount();
    }

}
