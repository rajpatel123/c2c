package com.chalojmd.activitis.rider;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.chalojmd.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class DropSearchActivity extends FragmentActivity implements OnMapReadyCallback {

    ImageView imageViewBack;
    GoogleMap gMap;
    SearchView searchView;
    SupportMapFragment supportMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_search);
        searchView = findViewById(R.id.search_bar);
        imageViewBack = findViewById(R.id.back1);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = searchView.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(DropSearchActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    try {
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        gMap.addMarker(new MarkerOptions().position(latLng).title(location));
                        gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    } catch (Exception e) {
                        Toast.makeText(DropSearchActivity.this, "Invalid Address", Toast.LENGTH_SHORT).show();
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        gMap = googleMap;



    }
}
