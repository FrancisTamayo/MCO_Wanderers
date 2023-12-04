package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button btnNewNote;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        loadJournalList ();
    }

    @Override
    protected void onResume () {
        super.onResume ();

        loadJournalList ();
    }


    public void loadJournalList () {
        RecyclerView recyclerJournal = findViewById(R.id.recyclerJournal);
        recyclerJournal.setHasFixedSize(true);
        recyclerJournal.setLayoutManager(new LinearLayoutManager(this));
        dbManager = new DBManager (this);
        dbManager.open ();
        Cursor cursor = dbManager.fetch ();

        List<MyJournalData> journalList = new ArrayList<MyJournalData>();

        if (cursor.moveToFirst ()) {
            int idIndex = cursor.getColumnIndex (DatabaseHelper._ID);
            int titleIndex = cursor.getColumnIndex (DatabaseHelper.TITLE);
            int contentIndex = cursor.getColumnIndex (DatabaseHelper.CONTENT);
            int dateMadeIndex = cursor.getColumnIndex (DatabaseHelper.DATE_MADE);
            int dateModifiedIndex = cursor.getColumnIndex (DatabaseHelper.DATE_MODIFIED);
            int locationIndex = cursor.getColumnIndex (DatabaseHelper.LOCATION);

            while (!cursor.isAfterLast ()) {
                long _id =  cursor.getLong (idIndex);
                String title = cursor.getString (titleIndex);
                String content = cursor.getString (contentIndex);
                long dateMade = cursor.getLong (dateMadeIndex);
                long dateModified = cursor.getLong (dateModifiedIndex);
                String location = cursor.getString (locationIndex);
                journalList.add (new MyJournalData (_id, title, content, dateMade, dateModified, location));

                cursor.moveToNext ();
            }
        }

        MyJournalData[] myJournalData = new MyJournalData[journalList.size ()];

        for (int i = 0; i < journalList.size (); i++)
            myJournalData[i] = journalList.get (i);

        MyJournalAdapter myJournalAdapter = new MyJournalAdapter(myJournalData, JournalActivity.this);
        recyclerJournal.setAdapter(myJournalAdapter);
    }

    public void newNote (View v) {
        PopupMenu popup = new PopupMenu (this, v);
        popup.setOnMenuItemClickListener (this);
        popup.inflate (R.menu.popup_new_note);
        popup.show ();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if (item.getItemId () == R.id.createNow) {
            Intent i = new Intent (this, NoteActivity.class);
            i.putExtra ("isNewNote", true);
            this.startActivity (i);
            return true;
        }
        else if (item.getItemId () == R.id.createMap) {
            Intent i = new Intent (this, MapActivity.class);
            i.putExtra ("isMap", true);
            this.startActivity (i);
            return true;
        }

        return false;
    }
}
