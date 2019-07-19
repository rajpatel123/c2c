package com.chalojmd.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chalojmd.R;
import com.chalojmd.activitis.NavigationActivity;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DirectionsStep;
import com.google.maps.model.EncodedPolyline;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

public class BookYourRideFragment extends Fragment implements
        OnMapReadyCallback,
        GoogleMap.OnMapLoadedCallback, LocationListener {

    private static final int GMS_PLACE_SEARCH_SOURCE_REQUEST_CODE = 51;
    private static final int GMS_PLACE_SEARCH_DEST_REQUEST_CODE = 52;
    private static final int _1_KM = 1000;
    // bunch of location related apis
    private FusedLocationProviderClient mFusedLocationClient;
    private SettingsClient mSettingsClient;
    private LocationRequest mLocationRequest;
    private LocationSettingsRequest mLocationSettingsRequest;
    private LocationCallback mLocationCallback;
    private Location mCurrentLocation;
    private Boolean mRequestingLocationUpdates;
    private SupportMapFragment mapFragment;
    private NavigationActivity navigationActivity;
    private GoogleMap mMap;
    LatLng sourceLocation;
    LatLng destLocation;
    private TextView calltodriver;
    RelativeLayout buttonRideNow;
    private BottomSheetBehavior sheetBehavior;
    private Button btnBottomSheet;
    private TextView sourceEdt;
    private TextView destEdt;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    ProgressBar progressBar;
    LinearLayout linearLayoutView,linearLayoutDriver;
    private Button confirmbutton;



    @Override
    public void onAttach(Context context) {


        super.onAttach(context);
        navigationActivity = (NavigationActivity) getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @SuppressLint("MissingPermission")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_your_ride_fragment, container, false);

        if (Build.VERSION.SDK_INT >= 21) {
            navigationActivity.getWindow().setStatusBarColor(ContextCompat.getColor(navigationActivity, R.color.transparent));
        }

        buttonRideNow=view.findViewById(R.id.relative);
        linearLayoutView=view.findViewById(R.id.bottom_sheet);
        navigationActivity.toolbar.setBackgroundColor(ContextCompat.getColor(navigationActivity, R.color.transparent));
        progressBar = view.findViewById(R.id.loader);
        sourceEdt = view.findViewById(R.id.source);
        linearLayoutDriver=view.findViewById(R.id.driverlinear);
        destEdt = view.findViewById(R.id.destination);
        calltodriver= view.findViewById(R.id.calldriver);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        confirmbutton= view.findViewById(R.id.confirmbooking);
        confirmbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutDriver.setVisibility(View.VISIBLE);
                linearLayoutView.setVisibility(View.GONE);

            }
        });

        buttonRideNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRideNow.setVisibility(View.GONE);
                linearLayoutView.setVisibility(View.VISIBLE);
            }
        });

        linearLayoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRideNow.setVisibility(View.VISIBLE);
                linearLayoutView.setVisibility(View.GONE);
            }
        });
        locationManager = (LocationManager) navigationActivity.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        calltodriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                callIntent.setData(Uri.parse("tel:" + "9315920796"));
                getActivity().startActivity(callIntent);
            }
        });



        sourceEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(navigationActivity);
                    startActivityForResult(intent, GMS_PLACE_SEARCH_SOURCE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        destEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                                    .build(navigationActivity);
                    startActivityForResult(intent, GMS_PLACE_SEARCH_DEST_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case GMS_PLACE_SEARCH_SOURCE_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(navigationActivity, data);
                    sourceEdt.setText(place.getName().toString());
                    sourceLocation = place.getLatLng();
                    drawPathWithBound();

                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(navigationActivity, data);
                    // TODO: Handle the error.
                    Log.i("Map", status.getStatusMessage());

                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
            case GMS_PLACE_SEARCH_DEST_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Place place = PlaceAutocomplete.getPlace(navigationActivity, data);
                    destEdt.setText(place.getName().toString());
                    destLocation = place.getLatLng();

                    drawPathWithBound();

                } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                    Status status = PlaceAutocomplete.getStatus(navigationActivity, data);
                    // TODO: Handle the error.
                    Log.i("Map", status.getStatusMessage());

                } else if (resultCode == RESULT_CANCELED) {
                    // The user canceled the operation.
                }
                break;
        }


    }
    private  void DistanceBetweenSourceAndDestination(){

        Location locationA = new Location("point A");
        locationA.setLatitude(sourceLocation.latitude);
        locationA.setLongitude(sourceLocation.longitude);

        Location locationB = new Location("point B");
        locationB.setLatitude(destLocation.latitude);
        locationB.setLongitude(destLocation.longitude);

        double distance = locationA.distanceTo(locationB)/1000;
        Toast.makeText(navigationActivity,""+distance+"km", Toast.LENGTH_SHORT).show();


    }

    private void drawPathWithBound() {
        if (sourceLocation != null && destLocation != null) {
            if (mMap == null) {
                return;
            }
            mMap.clear();
            LatLng barcelona = new LatLng(sourceLocation.latitude, sourceLocation.longitude);
            mMap.addMarker(new MarkerOptions().position(barcelona).title("Pick up").icon(BitmapDescriptorFactory.fromResource(R.drawable.location_iconping1)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 6));


            LatLng madrid = new LatLng(destLocation.latitude, destLocation.longitude);
            mMap.addMarker(new MarkerOptions().position(madrid).title("Drop").icon(BitmapDescriptorFactory.fromResource(R.drawable.location_iconping2)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(madrid, 6));

            //Define list to get all latlng for the route
            List<LatLng> path = new ArrayList();

            //Execute Directions API request
            GeoApiContext context = new GeoApiContext.Builder().apiKey(getString(R.string.google_maps_key))
                    .build();
            DirectionsApiRequest req = DirectionsApi.getDirections(context, sourceLocation.latitude + "," + sourceLocation.longitude, destLocation.latitude + "," + destLocation.longitude);
            try {
                DirectionsResult res = req.await();

                //Loop through legs and steps to get encoded polylines of each step
                if (res.routes != null && res.routes.length > 0) {
                    DirectionsRoute route = res.routes[0];

                    if (route.legs != null) {
                        for (int i = 0; i < route.legs.length; i++) {
                            DirectionsLeg leg = route.legs[i];
                            if (leg.steps != null) {
                                for (int j = 0; j < leg.steps.length; j++) {
                                    DirectionsStep step = leg.steps[j];
                                    if (step.steps != null && step.steps.length > 0) {
                                        for (int k = 0; k < step.steps.length; k++) {
                                            DirectionsStep step1 = step.steps[k];
                                            EncodedPolyline points1 = step1.polyline;
                                            if (points1 != null) {
                                                //Decode polyline and add points to list of route coordinates
                                                List<com.google.maps.model.LatLng> coords1 = points1.decodePath();
                                                for (com.google.maps.model.LatLng coord1 : coords1) {
                                                    path.add(new LatLng(coord1.lat, coord1.lng));
                                                }
                                            }
                                        }
                                    } else {
                                        EncodedPolyline points = step.polyline;
                                        if (points != null) {
                                            //Decode polyline and add points to list of route coordinates
                                            List<com.google.maps.model.LatLng> coords = points.decodePath();
                                            for (com.google.maps.model.LatLng coord : coords) {
                                                path.add(new LatLng(coord.lat, coord.lng));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                Log.e(TAG, ex.getLocalizedMessage());
            }

            //Draw the polyline
            if (path.size() > 0) {
                PolylineOptions opts = new PolylineOptions().addAll(path).color(Color.BLACK).width(11);
                mMap.addPolyline(opts);
                DistanceBetweenSourceAndDestination();

            }

                   //   yha hoga distance from travel

            mMap.getUiSettings().setZoomControlsEnabled(true);

           //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(zaragoza, 6));
        }
    }


    @Override
    public void onMapLoaded() {
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Set selected map style
        progressBar.setVisibility(View.VISIBLE);
        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {


            }
        });

        mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                progressBar.setVisibility(View.GONE);
            }
        });

        if (ActivityCompat.checkSelfPermission(navigationActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(navigationActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
    }


    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14);
        mMap.moveCamera(cameraUpdate);
        locationManager.removeUpdates(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }



}