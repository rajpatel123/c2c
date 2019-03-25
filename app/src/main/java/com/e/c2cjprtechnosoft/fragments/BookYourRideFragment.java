package com.e.c2cjprtechnosoft.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.activities.DropSearchActivity;
import com.e.c2cjprtechnosoft.activities.PickupSearchActivity;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class BookYourRideFragment extends Fragment implements OnMapReadyCallback {

    private CardView pickupLocation,dropLocation;
    private GoogleMap mMap;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_your_ride_fragment, container, false);
       getActivity().setTitle("My Ride");
        pickupLocation=view.findViewById(R.id.card_view_pickup_location);
        dropLocation=view.findViewById(R.id.card_view_drop_location);

        pickupLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PickupSearchActivity.class);
                startActivity(intent);
            }
        });
        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), DropSearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment=(SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap=googleMap;LatLng pp=new LatLng(11.5448729,104.8921668);
        MarkerOptions options=new MarkerOptions();
        options.position(pp).title("hgfdsfd");
        mMap.addMarker(options);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(pp));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        //mMap.setMinZoomPreference(5f);
        //   HARIPAD.distanceTo(MALIVAKA);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(41.19518983, -74.59716797))
                .title("LinkedIn")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(37.4629101, -122.2449094))
                .title("Facebook")
                .snippet("Facebook HQ: Menlo Park"));

        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.3092293, -122.1136845))
                .title("Apple"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.4233438,-122.0728817),16));
        mMap.setTrafficEnabled(true);



        // Changing map type
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);

        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(41.19518983, -74.59716797))
                .radius(10000)
                .strokeColor(Color.BLACK)
                .fillColor(Color.BLUE));

        // Showing / hiding your current location
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);

        // Enable / Disable zooming controls
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        // Enable / Disable my location button
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);

        // Enable / Disable Compass icon
        googleMap.getUiSettings().setCompassEnabled(true);

        // Enable / Disable Rotate gesture
        googleMap.getUiSettings().setRotateGesturesEnabled(true);

        // Enable / Disable zooming functionality
        googleMap.getUiSettings().setZoomGesturesEnabled(true);



    }
}
