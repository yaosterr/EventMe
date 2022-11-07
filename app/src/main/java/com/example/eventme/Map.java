package com.example.eventme;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Map extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;

    private double longitude = 0.0;
    private double latitude = 0.0;

    List<List<Address>> geoCoderList;
    List<String> eventNames; //Placeholder for event information we will have later

    private static final int LOCATION_PERMISSION_CODE = 101;

    FusedLocationProviderClient fusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().setTitle("Map");

        requestUserLocationPermission();

        if(isUserLocationPermissionGranted()) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.maps);
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mGoogleMap = googleMap;

        geoCoderList = new ArrayList<>();
        eventNames = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mGoogleMap.setMyLocationEnabled(true);
        }
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setZoomGesturesEnabled(true);

        // Make geolocations for event addresses and add event names to list
        try {
            geoCoderList.add(new Geocoder(this).getFromLocationName("University of Southern California, Los Angeles, CA", 1));
            eventNames.add("USC");
            geoCoderList.add(new Geocoder(this).getFromLocationName("2813 Elendale Place, Los Angeles, CA", 1));
            eventNames.add("Where Eric and Aristo live");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        if(!geoCoderList.isEmpty()) {
            for(int i = 0; i < geoCoderList.size(); i++) {
                latitude = geoCoderList.get(i).get(0).getLatitude();
                longitude = geoCoderList.get(i).get(0).getLongitude();

                Log.i("GOOGLE_MAP_TAG" + i, "Address has longitude: " + longitude + " latitude: " + latitude);

                LatLng event = new LatLng(latitude, longitude);
                mGoogleMap.addMarker(new MarkerOptions().position(event).title(eventNames.get(i))); // String could be event name instead of USC
                mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(event));
            }
        }
    }

    private boolean isUserLocationPermissionGranted() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestUserLocationPermission() {
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_CODE);
    }
}
