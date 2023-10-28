package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JournalActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    Button btnNewNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);


        RecyclerView recyclerJournal = findViewById(R.id.recyclerJournal);
        recyclerJournal.setHasFixedSize(true);
        recyclerJournal.setLayoutManager(new LinearLayoutManager(this));

        MyJournalData[] myJournalData = new MyJournalData[]{
                new MyJournalData("First Entry","Test"),
                new MyJournalData("I went to my house","Test2"),
                new MyJournalData("We went camping","Test3"),
        };

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
