package com.e.c2cjprtechnosoft.fragments;

import android.Manifest;
import android.content.Context;
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
import android.util.Log;
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

public class BookYourRideFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLoadedCallback {

    private CardView pickupLocation, dropLocation;
    private GoogleMap mMap;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_your_ride_fragment, container, false);
        getActivity().setTitle("My Ride");

        pickupLocation = view.findViewById(R.id.card_view_pickup_location);
        dropLocation = view.findViewById(R.id.card_view_drop_location);

        pickupLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PickupSearchActivity.class);
                startActivity(intent);
            }
        });
        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DropSearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                dropLocation.setVisibility(View.VISIBLE);
                pickupLocation.setVisibility(View.VISIBLE);
                Log.d("START","Camera Move start");
            }
        });
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                dropLocation.setVisibility(View.GONE);
                pickupLocation.setVisibility(View.GONE);
                Log.d("GONE","Camera Move On");
            }
        });
        mMap.setOnCameraMoveCanceledListener(new GoogleMap.OnCameraMoveCanceledListener() {
            @Override
            public void onCameraMoveCanceled() {
                dropLocation.setVisibility(View.VISIBLE);
                pickupLocation.setVisibility(View.VISIBLE);
                Log.d("VISIBLE","Camera Move OFF");
            }
        });

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        LatLng noida = new LatLng(28.595728, 77.339391);
        mMap.addMarker(new MarkerOptions().position(noida).title("Noida"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(noida,10.0F));
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(noida,10),5000,null);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            return;
        }
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(false);
        mMap.getUiSettings().setRotateGesturesEnabled(false);
        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.google_map_style1);
        mMap.setMapStyle(mapStyleOptions);

    }

    @Override
    public void onMapLoaded() {

        if (mMap != null) {
            mMap.snapshot((GoogleMap.SnapshotReadyCallback) getFragmentManager());
        }

    }
}
