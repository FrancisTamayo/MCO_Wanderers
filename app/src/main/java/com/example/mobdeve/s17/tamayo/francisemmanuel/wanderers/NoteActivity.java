package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.Date;

public class NoteActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    DBManager dbManager;
    EditText inputTitle, inputLocation, dateText, inputContent;
    private static final String TITLE_STATE_KEY = "savedTitle";
    private static final String LOCATION_STATE_KEY = "savedLocation";
    private static final String DATE_STATE_KEY = "savedDate";
    private static final String CONTENT_STATE_KEY = "savedContent";
    private static final String SP_FILE_NAME = "noteSP";
    Boolean isNewNote;
    long _id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView (R.layout.note_activity);

        dbManager = new DBManager (this);
        dbManager.open ();

        inputTitle = (EditText) findViewById (R.id.inputTitle);
        inputLocation = (EditText) findViewById (R.id.inputLocation);
        dateText = (EditText) findViewById (R.id.dateCreated);
        inputContent = (EditText) findViewById (R.id.inputNoteText);

        isNewNote = getIntent ().getExtras ().getBoolean ("isNewNote");

        if (!isNewNote) {
            _id = getIntent ().getExtras ().getLong (DatabaseHelper._ID);
            String title = getIntent ().getExtras ().getString (DatabaseHelper.TITLE);
            String location = getIntent ().getExtras ().getString (DatabaseHelper.LOCATION);
            String dateMade = getIntent ().getExtras ().getString (DatabaseHelper.DATE_MADE);
            String content = getIntent ().getExtras ().getString (DatabaseHelper.CONTENT);

            inputTitle.setText (title.toString ());
            inputLocation.setText (location.toString ());
            dateText.setText (dateMade.toString ());
            inputContent.setText (content.toString ());

            savePreferences ();
        }
        else {
            clearPreferences ();
        }
    }

    @Override
    protected void onResume () {
        super.onResume ();

        SharedPreferences sp = this.getSharedPreferences (SP_FILE_NAME, Context.MODE_PRIVATE);

        inputTitle.setText (sp.getString (TITLE_STATE_KEY, ""));
        inputLocation.setText (sp.getString (LOCATION_STATE_KEY, ""));
        dateText.setText (sp.getString (DATE_STATE_KEY, ""));
        inputContent.setText (sp.getString (CONTENT_STATE_KEY, ""));
    }

    @Override
    protected void onPause () {
        super.onPause ();

        savePreferences ();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy ();

        if (isFinishing ()) {
            String title = inputTitle.getText ().toString ().trim ();
            String location = inputLocation.getText ().toString ().trim ();
            String content = inputContent.getText ().toString ().trim ();

            if (isNewNote)
                dbManager.insert (title, content, location);
            else
                dbManager.update (_id, title, content, location);

            clearPreferences ();
        }
    }

    public void savePreferences () {
        SharedPreferences sp = this.getSharedPreferences (SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit ();

        editor.putString (TITLE_STATE_KEY, inputTitle.getText ().toString ());
        editor.putString (LOCATION_STATE_KEY, inputLocation.getText ().toString ());
        editor.putString (DATE_STATE_KEY, dateText.getText ().toString ());
        editor.putString (CONTENT_STATE_KEY, inputContent.getText ().toString ());
        editor.apply ();
    }

    public void clearPreferences () {
        SharedPreferences sp = this.getSharedPreferences (SP_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit ();

        editor.clear ();
        editor.commit ();
    }

    public void backToHome (View v) {
        finish ();
    }

    public void showDeletePopup (View v) {
        PopupMenu popup = new PopupMenu (this, v);
        popup.setOnMenuItemClickListener (this);;
        popup.inflate (R.menu.popup_delete);
        popup.show ();
    }

    public void showPhotoPopup (View v) {
        PopupMenu popup = new PopupMenu (this, v);
        popup.setOnMenuItemClickListener (this);
        popup.inflate (R.menu.popup_photo);
        popup.show ();
    }

    public void pickLocation (View v) {
        Intent i = new Intent (this, MapActivity.class);
        i.putExtra ("isMap", false);
        this.startActivity (i);
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
        else if (menuItem.getItemId () == R.id.confirmDelete) {
//            dbManager.delete (getIntent ().getExtras ().getLong (DatabaseHelper._ID));
            finish ();
        }
        return false;
    }
}