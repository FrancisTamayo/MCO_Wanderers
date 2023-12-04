package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.time.Instant;
import java.util.Date;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    public DBManager (Context c) { context = c; }

    public DBManager open () throws SQLException {
        dbHelper = new DatabaseHelper (context);
        database = dbHelper.getWritableDatabase ();
        return this;
    }

    public void close () { dbHelper.close (); }

    public void insert (String title, String content, String location) {
        Date c = new Date(System.currentTimeMillis ());
        long milliseconds = c.getTime ();
        ContentValues contentValues = new ContentValues ();

        contentValues.put (DatabaseHelper.TITLE, title);
        contentValues.put (DatabaseHelper.CONTENT, content);
        contentValues.put (DatabaseHelper.DATE_MADE, milliseconds);
        contentValues.put (DatabaseHelper.DATE_MODIFIED, milliseconds);
        contentValues.put (DatabaseHelper.LOCATION, location);

        database.insert (DatabaseHelper.TABLE_NAME, null, contentValues);
    }

    public Cursor fetch () {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.TITLE, DatabaseHelper.CONTENT,
        DatabaseHelper.DATE_MADE, DatabaseHelper.DATE_MODIFIED, DatabaseHelper.LOCATION };

        Cursor cursor = database.query (DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst ();

        return cursor;
    }

    public int update (long _id, String title, String content, String location) {
        Date c = new Date(System.currentTimeMillis ());
        long milliseconds = c.getTime ();
        ContentValues contentValues = new ContentValues ();

        contentValues.put (DatabaseHelper.TITLE, title);
        contentValues.put (DatabaseHelper.CONTENT, content);
        contentValues.put (DatabaseHelper.DATE_MADE, milliseconds);
        contentValues.put (DatabaseHelper.DATE_MODIFIED, milliseconds);
        contentValues.put (DatabaseHelper.LOCATION, location);

        int i = database.update (DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete (long _id) {
        database.delete (DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + " = " + _id, null);
    }
}
