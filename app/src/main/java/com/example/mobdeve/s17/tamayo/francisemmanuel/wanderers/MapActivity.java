package com.example.mobdeve.s17.tamayo.francisemmanuel.wanderers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {
    ImageView imgMap;
    private GoogleMap gMap;
    private UiSettings uiSettings;
    private Marker clickedMarker;
    private GoogleApiClient googleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.googleMaps);
        mapFragment.getMapAsync(this);
    }

    /*
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(0, 0))
                .title("Marker"));
    }*/
//    public void nextAct(View v) {
//        Intent nextIntent = new Intent(this, JournalActivity.class);
//
//        this.startActivity(nextIntent);
//        this.finish();
//    }

    public void locationPicked (View v) {
        //temp implementation
        Boolean isMap = getIntent ().getExtras ().getBoolean ("isMap");
        if (isMap) {
            Intent i = new Intent(this, NoteActivity.class);
            i.putExtra("isNewNote", true);
            this.startActivity (i);
        }
        finish ();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;
        uiSettings = gMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(true);


    }




}
