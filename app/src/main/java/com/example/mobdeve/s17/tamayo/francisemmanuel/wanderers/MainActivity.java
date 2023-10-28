package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent (this, NoteActivity.class);
        i.putExtra ("isNewNote", false);
        this.startActivity (i);
    }
}