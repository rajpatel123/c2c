package com.e.c2cjprtechnosoft.fragments;

import android.Manifest;
import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.DateFormat;
import java.util.Date;

public class BookYourRideFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnMapLoadedCallback
        /*GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener*/ {

    int PLACE_PICKER_REQUEST = 1;
    private CardView pickupLocation, dropLocation;
    private GoogleMap mMap;
    private LinearLayout linearLayout;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int REQUEST_USER_LOCATION_CODE = 99;

    private String mLastUpdateTime;

    // location updates interval - 10sec
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;

    // fastest updates interval - 5 sec
    // location updates will be received if another app is requesting the locations
    // than your app can handle
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;

    private static final int REQUEST_CHECK_SETTINGS = 100;


    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_your_ride_fragment, container, false);
        getActivity().setTitle("My Ride");
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }*/
        // checkUserLocationPermission();
        linearLayout = view.findViewById(R.id.linear_bottom);

        ((ViewGroup) view.findViewById(R.id.root)).getLayoutTransition()
                .enableTransitionType(LayoutTransition.CHANGING);


        pickupLocation = view.findViewById(R.id.card_view_pickup_location);
        dropLocation = view.findViewById(R.id.card_view_drop_location);

        pickupLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "fjf", Toast.LENGTH_SHORT).show();

            }
        });
        dropLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Coming Soon", Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(getActivity(), DropSearchActivity.class);
                startActivity(intent);*/
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
        if (ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity()
                , Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            // buildgoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

      /*  mMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {
            @Override
            public void onCameraMoveStarted(int i) {
                dropLocation.setVisibility(View.VISIBLE);
                pickupLocation.setVisibility(View.VISIBLE);
                Log.d("START", "Camera Move start");
            }
        });*/
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {
                dropLocation.setVisibility(View.GONE);
                pickupLocation.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
                Log.d("GONE", "Camera Move On");
            }
        });
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {
                dropLocation.setVisibility(View.VISIBLE);
                pickupLocation.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                Log.d("VISIBLE", "Camera Changes");
            }
        });


        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        // googleMap.setMapType(GoogleMap.MAP_TYPE_NONE);
        LatLng noida = new LatLng(28.595728, 77.339391);
        mMap.addMarker(new MarkerOptions().position(noida).title("Noida"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(noida, 10.0F));
      //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(noida, 15), 3000, null);

        //mMap.setMinZoomPreference(5f);
        //   HARIPAD.distanceTo(MALIVAKA);
       // mMap.getUiSettings().setZoomGesturesEnabled(true);
       // mMap.getUiSettings().setRotateGesturesEnabled(true);

        googleMap.setTrafficEnabled(true);

        // Enable / Disable zooming controls
       // mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
       // mMap.getUiSettings().setCompassEnabled(true);
        //mMap.getUiSettings().setZoomControlsEnabled(true);
        //mMap.getUiSettings().setZoomGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        MapStyleOptions mapStyleOptions = MapStyleOptions.loadRawResourceStyle(getActivity(), R.raw.google_map_style);
        mMap.setMapStyle(mapStyleOptions);

    }





    @Override
    public void onMapLoaded() {

        if (mMap != null) {
            mMap.snapshot((GoogleMap.SnapshotReadyCallback) getFragmentManager());
        }

    }








}
