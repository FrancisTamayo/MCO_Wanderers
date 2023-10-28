package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean isNewNote = getIntent ().getExtras ().getBoolean ("isNewNote");
        if (isNewNote) {
            setContentView(R.layout.blank_note_activity);
            Toast.makeText (this, "Separate blank layout just for UI purposes", Toast.LENGTH_SHORT).show ();
        }
        else
            setContentView(R.layout.note_activity);
    }

    public void backToHome (View v) {
        finish ();
    }

    public void deleteNote (View v) {
        //TODO: delete note logic
        backToHome (v);
    }

    public void showPhotoPopup (View v) {
        PopupMenu popup = new PopupMenu (this, v);
        popup.setOnMenuItemClickListener (this);
        popup.inflate (R.menu.popup_photo);
        popup.show ();
    }

    public void pickLocation (View v) {
        //TODO: Intent i = new Intent (this, MAP_ACTIVITY.class);
        //this.startActivity (i);
        Toast.makeText (this, "Location would be updated", Toast.LENGTH_SHORT).show ();
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId () == R.id.choosePhoto) {
            Toast.makeText (this, "Photo will be chosen from library", Toast.LENGTH_SHORT).show ();
            return true;
        }
        else if (menuItem.getItemId () == R.id.takePhoto) {
            Toast.makeText (this, "Camera will be opened", Toast.LENGTH_SHORT).show ();
            return true;
        }
        return false;
    }
}