package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class JournalActivity extends AppCompatActivity {
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
}
