package com.e.c2cjprtechnosoft.Activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.e.c2cjprtechnosoft.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (mMap != null) {

            mMap.setOnMapLoadedCallback(this);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

      /*  // Add a marker in Sydney and move the camera
       LatLng noida = new LatLng(33.785022, 72.721992);
        mMap.addMarker(new MarkerOptions().position(noida).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(noida,10.0F));

*/


    }

    @Override
    public void onMapLoaded() {

    }
}

