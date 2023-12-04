package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "NOTES";

    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String DATE_MADE = "dateMade";
    public static final String DATE_MODIFIED = "dateModified";
    public static final String LOCATION = "location";

    static final String DB_NAME = "NOTES_LIST.DB";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL, " + CONTENT + " TEXT, "
            + DATE_MADE + " INTEGER, " + DATE_MODIFIED + " INTEGER, " + LOCATION + " TEXT);";

    public DatabaseHelper (Context context) { super (context, DB_NAME, null, DB_VERSION); }

    @Override
    public void onCreate (SQLiteDatabase db) { db.execSQL (CREATE_TABLE); }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL ("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate (db);
    }
}
