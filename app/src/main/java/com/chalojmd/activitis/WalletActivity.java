package com.chalojmd.activitis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.chalojmd.R;

public class WalletActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        if (getSupportActionBar() != null){

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()== android.R.id.home) {

            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


//package com.camtrack.tracker.activities;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Rect;
//import android.location.Address;
//import android.location.Geocoder;
//import android.location.Location;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.annotation.NonNull;
//import android.support.v4.content.ContextCompat;
//import android.support.v4.content.LocalBroadcastManager;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.AppCompatButton;
//import android.support.v7.widget.AppCompatEditText;
//import android.support.v7.widget.Toolbar;
//import android.text.TextUtils;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.MotionEvent;
//import android.view.View;
//import android.view.ViewTreeObserver;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.SeekBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.camtrack.tracker.R;
//import com.camtrack.tracker.constants.Constants;
//import com.camtrack.tracker.db.RealDataInterfaceHelper;
//import com.camtrack.tracker.enums.POIType;
//import com.camtrack.tracker.googlemap.AppGoogleLocationClient;
//import com.camtrack.tracker.googlemap.GoogleAutoCompleteActivity;
//import com.camtrack.tracker.mapbox.MapBoxLocationAddressManager;
//import com.camtrack.tracker.models.device.TripHistoryData;
//import com.camtrack.tracker.models.unit.UnitModel;
//import com.camtrack.tracker.thirdparties.RealmUserV2;
//import com.camtrack.tracker.utils.AppLoger;
//import com.camtrack.tracker.utils.KentSecurityPrefs;
//import com.camtrack.tracker.utils.LocationAddressManager;
//import com.camtrack.tracker.utils.UtilsApp;
//import com.camtrack.tracker.verticalseekbar.VerticalSeekBar;
//import com.github.angads25.toggle.interfaces.OnToggledListener;
//import com.github.angads25.toggle.model.ToggleableView;
//import com.github.angads25.toggle.widget.LabeledSwitch;
//import com.google.android.gms.location.places.Place;
//import com.mapbox.android.core.permissions.PermissionsListener;
//import com.mapbox.android.core.permissions.PermissionsManager;
//import com.mapbox.geojson.Feature;
//import com.mapbox.geojson.FeatureCollection;
//import com.mapbox.geojson.Point;
//import com.mapbox.mapboxsdk.Mapbox;
//import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
//import com.mapbox.mapboxsdk.geometry.LatLng;
//import com.mapbox.mapboxsdk.location.LocationComponent;
//import com.mapbox.mapboxsdk.location.modes.CameraMode;
//import com.mapbox.mapboxsdk.maps.MapView;
//import com.mapbox.mapboxsdk.maps.MapboxMap;
//import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
//import com.mapbox.mapboxsdk.style.layers.CircleLayer;
//import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
//import com.mapbox.mapboxsdk.style.layers.PropertyValue;
//import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Locale;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import io.reactivex.Observable;
//import io.reactivex.ObservableEmitter;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.schedulers.Schedulers;
//import io.realm.Realm;
//import io.realm.RealmResults;
//import okhttp3.internal.Util;
//
//public class POIDetailsActivity extends BaseActivity implements PermissionsListener {
//
//    private final String TAG = "POIDetailsActivity";
//    private static final int GMS_PLACE_SEARCH_REQUEST_CODE = 51;
//    private static final int _1_KM = 1000;
//    private static final int defaultMinRadius = 100;    // in meters
//    private static final int defaultMaxRadius = 5000;   // in meters
//
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;
//    @BindView(R.id.edtNameOfPlace)
//    AppCompatEditText edtNameOfPlace;
//    @BindView(R.id.chBoxIn)
//    LabeledSwitch chBoxIn;
//    @BindView(R.id.chBoxOut)
//    LabeledSwitch chBoxOut;
//    @BindView(R.id.btnSaveLocation)
//    AppCompatButton btnSaveLocation;
//    @BindView(R.id.rlPoiDetails)
//    LinearLayout rlPoiDetails;
//    @BindView(R.id.vh_seekbar)
//    RelativeLayout seekBarWrapper;
//    @BindView(R.id.vh_seekvalue)
//    LinearLayout progressContainer;
//    @BindView(R.id.txvSeekBarValue)
//    TextView tvProgress;
//    @BindView(R.id.skbSample)
//    VerticalSeekBar seekBar;
//    @BindView(R.id.txtSearchResult)
//    TextView txtSearchResult;
//    @BindView(R.id.deleteSearchResult)
//    ImageView deleteSearchResult;
//    @BindView(R.id.editImageView)
//    ImageView editImageView;
//    @BindView(R.id.vh_search)
//    RelativeLayout vh_search;
//
//    @BindView(R.id.vh_frame)
//    FrameLayout vh_frame;
//
//    @BindView(R.id.vh_poi_holder)
//    RelativeLayout vh_poi_holder;
//
//    boolean isRadiusInDefaultUnit = true;
//    int calculatedRadius;
//    private MapboxMap googleMap;
//    private com.camtrack.tracker.entities.Place oldPlace;
//    private UnitModel unitModel;
//    private int minRadiusInMeters = defaultMinRadius, maxRadiusInMeters = defaultMaxRadius;
//    private int defaultRadius = defaultMinRadius;
//    private boolean isCameraMoveStarted;
//    private Handler handler = new Handler();
//    private LatLng currentLocation = null;
//    private LatLng dragLocation = null;
//
//    private PermissionsManager permissionsManager;
//    private MapView mapView;
//    private int SEEK_PROGRESS = 0;
//    private AppGoogleLocationClient googleLocClient;
//
//    // TODO: Please check {AppGoogleLocationClient to implement for custom}
//    private CircleLayer circleLayer;
//    private GeoJsonSource geoJsonSource;
//    private String markerSourceId = "marker-source";
//    private String markerLayerId = "marker-layer";
//    private boolean firstSeek = true;
//    private boolean showDeletePOIMenuItem;
//    private boolean isFirstVisit = true;
//    private boolean isEditable = false;
//    private long placeId = 0;
//
//
//    public static void startForResult(AppCompatActivity compatActivity, com.camtrack.tracker.entities.Place place,
//                                      String minAreaValue, String maxAreaValue,boolean isFenceSettingEnabled,
//                                      String fenceData, int requestCode,long id) {
//
//        Intent intent = new Intent(compatActivity, POIDetailsActivity.class);
//        intent.putExtra("place", place);
//        intent.putExtra(Constants.GEO_DISTANCE_MIN, minAreaValue);
//        intent.putExtra(Constants.GEO_DISTANCE_MAX, maxAreaValue);
//        intent.putExtra(Constants.IS_SETTING_ENABLED, isFenceSettingEnabled);
//        intent.putExtra(Constants.FENCE_DATA, fenceData);
//        intent.putExtra("id", id);
//
//        compatActivity.startActivityForResult(intent, requestCode);
//    }
//
//    private void setUiSetting() {
//        googleMap.getUiSettings().setZoomGesturesEnabled(true);
//        googleMap.getUiSettings().setScrollGesturesEnabled(true);
//        googleMap.getUiSettings().setLogoEnabled(false);
//        googleMap.getUiSettings().setAttributionEnabled(false);
//        googleMap.getUiSettings().setRotateGesturesEnabled(false);
//        googleMap.getUiSettings().setCompassEnabled(false);
//        googleMap.setMinZoomPreference(Constants.Location.DEFAULT_ZOOM_8);
//    }
//
//    private void createLayer(LatLng latLng) {
//        createLayer(latLng, false);
//    }
//
//    private void createLayer(LatLng latLng, boolean reset) {
//        if (googleMap == null || latLng == null) {
//            return;
//        }
//        if (circleLayer != null) {
//            List<Feature> markerCoordinates = new ArrayList<>();
//            Point         point             = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
//            markerCoordinates.add(Feature.fromGeometry(point));
//            FeatureCollection featureCollection = FeatureCollection.fromFeatures(markerCoordinates);
//            geoJsonSource.setGeoJson(featureCollection);
//
//            int progress = seekBar.getProgress();
//            if (reset) {
//                progress = 70;
//            } else {
//                progress = 70 + (progress - SEEK_PROGRESS) / 70;
//            }
//
//            circleLayer.setProperties(PropertyFactory.circleRadius(Float.valueOf((progress))));
//            return;
//        }
//
//        if (googleMap.getStyle() != null) {
//            List<Feature> markerCoordinates = new ArrayList<>();
//            Point         point             = Point.fromLngLat(latLng.getLongitude(), latLng.getLatitude());
//            markerCoordinates.add(Feature.fromGeometry(point));
//            FeatureCollection featureCollection = FeatureCollection.fromFeatures(markerCoordinates);
//
//            geoJsonSource = new GeoJsonSource(markerSourceId);
//            geoJsonSource.setGeoJson(featureCollection);
//            googleMap.getStyle().addSource(geoJsonSource);
//
//            circleLayer = new CircleLayer(markerLayerId, markerSourceId)
//                    .withProperties(PropertyFactory.circleRadius(70f),
//                               /*PropertyFactory.circleStrokeWidth(1f),
//                               PropertyFactory.circleStrokeColor(Color.BLACK),*/
//                            PropertyFactory.circleColor(getResources().getColor(R.color.dark_sky_blue_transparent)));
//
//            googleMap.getStyle().addLayer(circleLayer);
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Mapbox.getInstance(this, getString(R.string.mapbox_access_token));
//        setContentView(R.layout.activity_poi_details);
//        ButterKnife.bind(this);
//
//        googleLocClient = new AppGoogleLocationClient(this);
//        googleLocClient.connectGoogleApiClient();
//
//        setSupportActionBar(toolbar);
//        if (getSupportActionBar() != null) {
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//            getSupportActionBar().setDisplayShowHomeEnabled(true);
//        }
//
//        Intent receivedIntent = getIntent();
//
//        if (receivedIntent != null && receivedIntent.hasExtra("place")
//                && receivedIntent.getParcelableExtra("place") != null) {
//
//            oldPlace = receivedIntent.getParcelableExtra("place");
//            if (oldPlace != null) {
//                editImageView.setVisibility(View.VISIBLE);
//                showDeletePOIMenuItem = true;
//                isEditable = true;
//                if (!TextUtils.isEmpty(oldPlace.getName())) {
//                    toolbar.setTitle(oldPlace.getName());
//                } else {
//                    toolbar.setTitle(getString(R.string.edit_place_of_interest));
//                }
//                SEEK_PROGRESS = (int) oldPlace.getRadius();
//            } else {
//                editImageView.setVisibility(View.GONE);
//                toolbar.setTitle(getString(R.string.add_place_of_interest));
//                showDeletePOIMenuItem = false;
//                isEditable = false;
//            }
//        }
//
//        if(receivedIntent!=null && receivedIntent.hasExtra("id")){
//            placeId = receivedIntent.getLongExtra("id",0);
//        }
//
//        handler.postDelayed(this::invalidateOptionsMenu, 320);
//
//        if (receivedIntent != null && receivedIntent.hasExtra(Constants.GEO_DISTANCE_MIN)) {
//            String minAreaValue = receivedIntent.getStringExtra(Constants.GEO_DISTANCE_MIN);
//            if (!TextUtils.isEmpty(minAreaValue)) {
//                minRadiusInMeters = Integer.parseInt(getIntent().getStringExtra(Constants.GEO_DISTANCE_MIN));
//                if (minRadiusInMeters < defaultMinRadius) {
//                    minRadiusInMeters = defaultMinRadius;
//                }
//            }
//        }
//
//        if (receivedIntent != null && receivedIntent.hasExtra(Constants.GEO_DISTANCE_MAX)) {
//            String maxAreaValue = receivedIntent.getStringExtra(Constants.GEO_DISTANCE_MAX);
//            if (!TextUtils.isEmpty(maxAreaValue)) {
//                maxRadiusInMeters = Integer.parseInt(getIntent().getStringExtra(Constants.GEO_DISTANCE_MAX));
//                if (maxRadiusInMeters <= defaultMinRadius) {
//                    maxRadiusInMeters = defaultMaxRadius;
//                }
//            }
//        }
//
//        if (oldPlace != null && oldPlace.getRadius() > 0) {
//            defaultRadius = (int) oldPlace.getRadius();
//            uiWhenPoiUpdate();
//        } else {
//            defaultRadius = minRadiusInMeters;
//            uiWhenPoiAdd();
//        }
//
//        /*if (receivedIntent != null && receivedIntent.hasExtra(Constants.IS_SETTING_ENABLED)) {
//            boolean isFenceSettingEnabled = receivedIntent.getBooleanExtra(Constants.IS_SETTING_ENABLED, false);
//        }
//
//        if (receivedIntent != null && receivedIntent.hasExtra(Constants.FENCE_DATA)) {
//            String fenceData = receivedIntent.getStringExtra(Constants.FENCE_DATA);
//        }*/
//
//        mapView = findViewById(R.id.mapView_poi);
//        mapView.onCreate(savedInstanceState);
//        mapView.getMapAsync(onMapReadyCallback);
//
////        setRealmMinMax();
//
//        seekBar.setMax(maxRadiusInMeters);
//        seekBar.setMin(minRadiusInMeters);
//
//        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                if (fromUser) {
//                    onSeekChanged(progress);
//                } else if (firstSeek) {
//                    firstSeek = false;
//                    onSeekChanged(progress);
//                }
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//                uiWhenPoiEditable();
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//                SEEK_PROGRESS = seekBar.getProgress();
//                resetMapZoomOnSeekLeft();
//                setMarkerAndCircle(oldPlace);
//            }
//        });
//
//        calculatedRadius = defaultRadius - minRadiusInMeters;
//        if (calculatedRadius <= 0) {
//            onSeekChanged(0);
//            seekBar.setProgress(0);
//        } else {
//            seekBar.setProgress(calculatedRadius);
//        }
//
//
//        setSoftKeyPad();
//        setToggleClicks();
//    }
//
//    /**
//     * Set Toggle CLICKS for Showing Update Button
//     */
//    private void setToggleClicks() {
//        if(chBoxIn!=null){
//            chBoxIn.setOnToggledListener(new OnToggledListener() {
//                @Override
//                public void onSwitched(ToggleableView toggleableView,
//                                       boolean isOn) {
//                    if(isEditable){
//                        uiWhenPoiEditable();
//                    }
//
//                }
//            });
//        }
//
//        if(chBoxOut!=null){
//            chBoxOut.setOnToggledListener(new OnToggledListener() {
//                @Override
//                public void onSwitched(ToggleableView toggleableView,
//                                       boolean isOn) {
//                    if(isEditable){
//                        uiWhenPoiEditable();
//                    }
//
//                }
//            });
//        }
//    }
//
//    private void setRealmMinMax() {
//        try {
//            Realm realm = Realm.getDefaultInstance();
//            unitModel = realm.where(UnitModel.class).findAll().first();
//            if (unitModel != null) {
//                String strMinRadius;
//                String strMaxRadius;
//
//                isRadiusInDefaultUnit = unitModel.isDistance();
//
//                if (isRadiusInDefaultUnit) {
//                    strMinRadius = minRadiusInMeters + " m";
//                    strMaxRadius = (maxRadiusInMeters >= _1_KM) ? (maxRadiusInMeters / _1_KM + " KM") : (maxRadiusInMeters + " m");
//
//                } else {
//                    strMinRadius = String.format(Locale.ENGLISH, "%.02f", meters2Miles(minRadiusInMeters)) + " MILE";
//                    strMaxRadius = String.format(Locale.ENGLISH, "%.02f", meters2Miles(maxRadiusInMeters)) + " MILE";
//                }
//                //tvMin.setText(strMinRadius);
//                //tvMax.setText(strMaxRadius);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void resetMapZoomOnSeekLeft() {
//        try {
//            if (calculatedRadius > 0) {
//                if (googleMap != null) {
//                    LatLng latLng = (dragLocation != null ? dragLocation : currentLocation);
//
//                    int    zoomLevel = 14;
//                    double radius    = calculatedRadius * 2;
//                    double scale     = radius / 500;
//                    zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
//
//                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void onSeekChanged(final int progress) {
//        //progressContainer.postDelayed(() -> {
//        progressContainer.setY(seekBarWrapper.getHeight() -
//                (seekBar.getThumbCenter() + UtilsApp.dp2px(15)));
//        calculatedRadius = progress + minRadiusInMeters;
//
//        String strProgress;
//
//        if (isRadiusInDefaultUnit) {
//            if ((calculatedRadius >= _1_KM)) {
//                if (calculatedRadius % _1_KM == 0) {
//                    strProgress = String.format(Locale.ENGLISH, "%d", (int) (calculatedRadius / (float) _1_KM)) + " KM";
//                } else {
//                    strProgress = String.format(Locale.ENGLISH, "%.02f", (calculatedRadius / (float) _1_KM)) + " KM";
//                }
//            } else {
//                strProgress = calculatedRadius + " m";
//            }
//        } else {
//            strProgress = String.format(Locale.ENGLISH, "%.02f", meters2Miles(calculatedRadius)) + " MILE";
//        }
//        tvProgress.setText(strProgress);
//
//        createLayer(dragLocation != null ? dragLocation : currentLocation);
//        if (oldPlace != null) {
//            oldPlace.setRadius(calculatedRadius);
//            resetUI(oldPlace);
//        }
//        // }, 10);
//    }
//
//    @OnClick(R.id.vh_search)
//    public void onSearchViewClick() {
//        onSearchClick();
//    }
//
//    @OnClick({R.id.editImageViewLL,R.id.editImageView})
//    public void onEditImageViewClick() {
//        edtNameOfPlace.setClickable(true);
//        edtNameOfPlace.setCursorVisible(true);
//        edtNameOfPlace.setFocusable(true);
//        edtNameOfPlace.setFocusableInTouchMode(true);
//        edtNameOfPlace.setEnabled(true);
//        edtNameOfPlace.requestFocus();
//        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        if (imm != null) {
//            imm.showSoftInput(edtNameOfPlace, InputMethodManager.SHOW_FORCED);
//        }
//        disableTouch();
//        uiWhenPoiEditable();
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.poi_details_menu, menu);
//        MenuItem item = menu.findItem(R.id.action_delete);
//        item.setVisible(showDeletePOIMenuItem);
//        return true;
//    }
//
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        if (menu != null) {
//            MenuItem item = menu.findItem(R.id.action_delete);
//            item.setVisible(showDeletePOIMenuItem);
//        }
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                onBackPressed();
//                return true;
//
//            case R.id.action_delete:
//                deletePOIAlert();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    private void onSearchClick() {
//        GoogleAutoCompleteActivity.startSearchScreenForResult(this, getString(R.string.place_of_interest), GMS_PLACE_SEARCH_REQUEST_CODE);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK && requestCode == GMS_PLACE_SEARCH_REQUEST_CODE) {
//            com.camtrack.tracker.googlemap.Place mPlace = data.getParcelableExtra("result");
//            if (mPlace != null) {
//                LocationAddressManager.getLocation(googleLocClient.getGoogleApiClient(), mPlace.getPlaceId(), data1 -> runOnUiThread(() -> onSetData(data1)));
//            }
//        }
//    }
//
//    private void onSetData(Place placeGMS) {
//        if (placeGMS != null) {
//            if (oldPlace == null) {
//                oldPlace = new com.camtrack.tracker.entities.Place();
//                oldPlace.setId(UtilsApp.getTimeInSeconds());
//                oldPlace.setRadius(defaultRadius);
//            }
//
//            oldPlace.setName(edtNameOfPlace.getText().toString());
//            oldPlace.setLatitude(placeGMS.getLatLng().latitude);
//            oldPlace.setLongitude(placeGMS.getLatLng().longitude);
//            oldPlace.setUpdatedOn(UtilsApp.getTimeInSeconds());
//            oldPlace.setAddress1(placeGMS.getName().toString());
//            oldPlace.setAddress2(placeGMS.getAddress().toString());
//
//            if (chBoxIn.isOn() && chBoxOut.isOn()) {
//                oldPlace.setType(POIType.fromValue(POIType.BOTH));
//                oldPlace.setEnabled(true);
//            } else if (chBoxIn.isOn()) {
//                oldPlace.setType(POIType.fromValue(POIType.ENTRY));
//                oldPlace.setEnabled(true);
//            } else if (chBoxOut.isOn()) {
//                oldPlace.setType(POIType.fromValue(POIType.EXIT));
//                oldPlace.setEnabled(true);
//            } else {
//                oldPlace.setType("");
//                oldPlace.setEnabled(false);
//            }
//
//            resetUI(oldPlace);
//            setMarkerAndCircle(oldPlace);
//            setDefaultZoom(oldPlace.getLatitude(), oldPlace.getLongitude());
//
//            uiWhenPoiEditable();
//        }
//    }
//
//    @OnClick(R.id.btnSaveLocation)
//    public void onSaveLocationBtnClick() {
//        enableTouch();
//        if (oldPlace == null) {
//            Toast.makeText(POIDetailsActivity.this, "Set place of interest", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (TextUtils.isEmpty(edtNameOfPlace.getText().toString())) {
//            Toast.makeText(POIDetailsActivity.this, "Enter name of place", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        if (!chBoxIn.isOn() && !chBoxOut.isOn()) {
//            Toast.makeText(POIDetailsActivity.this, "Check fence type", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        com.camtrack.tracker.entities.Place place = new com.camtrack.tracker.entities.Place();
//        if(placeId>0){
//            place.setId(placeId);
//        }else{
//            place.setId(oldPlace.getId());
//        }
//        place.setName(edtNameOfPlace.getText().toString());
//        place.setLatitude(oldPlace.getLatitude());
//        place.setLongitude(oldPlace.getLongitude());
//        place.setRadius(oldPlace.getRadius());
//        place.setUpdatedOn(oldPlace.getUpdatedOn());
//        place.setAddress1(oldPlace.getAddress1());
//        place.setAddress2(oldPlace.getAddress2());
//
//        if (chBoxIn.isOn() && chBoxOut.isOn()) {
//            place.setType(POIType.fromValue(POIType.BOTH));
//            place.setEnabled(true);
//        } else if (chBoxIn.isOn()) {
//            place.setType(POIType.fromValue(POIType.ENTRY));
//            place.setEnabled(true);
//        } else if (chBoxOut.isOn()) {
//            place.setType(POIType.fromValue(POIType.EXIT));
//            place.setEnabled(true);
//        } else {
//            place.setType("");
//            place.setEnabled(false);
//        }
//
//        try {
//            String deviceSerialNumber = KentSecurityPrefs.getString(POIDetailsActivity.this, Constants.DEVICE_SERIALNUMBER);
//
//            RealmUserV2 deviceUser = RealmUserV2.getInstance(deviceSerialNumber);
//            Realm       realm      = deviceUser.getRealm();
//            // TODO: 6/22/2018 You can put this kind of operation  executeTransactionAsync to better performance
//            if (realm != null) {
//                realm.beginTransaction();
//                realm.copyToRealmOrUpdate(place);
//                realm.commitTransaction();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Intent intent = new Intent();
//        intent.putExtra("isNewPOIAdded", true);
//        setResult(Activity.RESULT_OK, intent);
//        finish();
//    }
//
//    @OnClick(R.id.rlMyLocation)
//    public void onViewClicked() {
//        dragLocation = null;
//        if (currentLocation != null) {
//            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()), 14f));
//        }
////        txtSearchResult.setText(getCurrentAddress(currentLocation));
//
//        txtSearchResult.setText("Loading...");
//        setPlaceFormLatLongFromMapBox(currentLocation.getLatitude(), currentLocation.getLongitude());
//
//    }
//
//
//
//    private String getCurrentAddress(LatLng currentLocation){
//        Geocoder geocoder;
//        List<Address> addresses;
//        geocoder = new Geocoder(this, Locale.getDefault());
//        String address="", city="";
//
//
//        try {
//            addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
//
//            if(addresses.size()>0) {
//                address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
//                city = addresses.get(0).getLocality();
//                String state = addresses.get(0).getAdminArea();
//                String country = addresses.get(0).getCountryName();
//                String postalCode = addresses.get(0).getPostalCode();
//                String knownName = addresses.get(0).getFeatureName();
//                if(city==null) {
//                    city = "";
//                }
//                else if(city!=null && city.equals("null")) {
//                    city = "";
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return city+" "+address;
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//
//    private void setPlaceFormLatLongFromMapBox(double latitude, double longitude) {
//        try {
//            Observable<String> objectObservable = Observable.create((ObservableEmitter<String> subscriber) -> {
//                try {
//                    String address = MapBoxLocationAddressManager.getAddressByLatLng(getApplicationContext(), latitude, longitude);
//                    subscriber.onNext(address);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//
//            Disposable subscribe = objectObservable.subscribe(address -> {
//                try {
//                    if (address != null) {
//                        if (oldPlace == null) {
//                            oldPlace = new com.camtrack.tracker.entities.Place();
//                            oldPlace.setId(UtilsApp.getTimeInSeconds());
//                            oldPlace.setRadius(defaultRadius);
//                        }
//
//                        oldPlace.setName(edtNameOfPlace.getText().toString());
//                        oldPlace.setLatitude(latitude);
//                        oldPlace.setLongitude(longitude);
//                        oldPlace.setUpdatedOn(UtilsApp.getTimeInSeconds());
//                        String[] addr = address.split(",");
//                        oldPlace.setAddress1(addr[0]);
//                        oldPlace.setAddress2(address);
//
//                        if (chBoxIn.isOn() && chBoxOut.isOn()) {
//                            oldPlace.setType(POIType.fromValue(POIType.BOTH));
//                            oldPlace.setEnabled(true);
//                        } else if (chBoxIn.isOn()) {
//                            oldPlace.setType(POIType.fromValue(POIType.ENTRY));
//                            oldPlace.setEnabled(true);
//                        } else if (chBoxOut.isOn()) {
//                            oldPlace.setType(POIType.fromValue(POIType.EXIT));
//                            oldPlace.setEnabled(true);
//                        } else {
//                            oldPlace.setType("");
//                            oldPlace.setEnabled(false);
//                        }
//
//                        resetUI(oldPlace);
//                        setMarkerAndCircle(oldPlace);
//
//                        handler.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                updateSeekBar();
//                                if(!isFirstVisit){
//                                    uiWhenPoiEditable();
//                                }else{
//                                    isFirstVisit = false;
//                                }
////                                updateSeekBarOnMapActions();
//                            }
//                        }, 10);
//
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    private void updateSeekBar(){
//        progressContainer.setY(seekBarWrapper.getHeight() -
//                (seekBar.getThumbCenter() + UtilsApp.dp2px(15)));
//        calculatedRadius = seekBar.getProgress() + minRadiusInMeters;
//
//        String strProgress;
//        if (isRadiusInDefaultUnit) {
//            if ((calculatedRadius >= _1_KM)) {
//                if (calculatedRadius % _1_KM == 0) {
//                    strProgress = String.format(Locale.ENGLISH, "%d", (int) (calculatedRadius / (float) _1_KM)) + " KM";
//                } else {
//                    strProgress = String.format(Locale.ENGLISH, "%.02f", (calculatedRadius / (float) _1_KM)) + " KM";
//                }
//            } else {
//                strProgress = calculatedRadius + " m";
//            }
//        } else {
//            strProgress = String.format(Locale.ENGLISH, "%.02f", meters2Miles(calculatedRadius)) + " MILE";
//        }
//        tvProgress.setText(strProgress);
//    }
//    /**
//     * JUGAAD Method  for Update SeekBar on Map Zoom IN/OUT
//     */
//    private void updateSeekBarOnMapActions() {
//        try {
//            float distance = UtilsApp.getDistance(googleMap);
////            distance = distance + (float) (distance / 0.3);
//
//            PropertyValue<Float> circleRadius = circleLayer.getCircleRadius();
//            if (circleRadius != null && circleRadius.isValue()) {
//                int deviceWidth = UtilsApp.deviceWidth(this);
//
//                int percentage = (int) ((circleRadius.getValue() * 2 / deviceWidth) * 100);
//
//                int seekDistance = (int) ((distance * percentage) / 100);
//
//                if (seekBar != null) {
//                    AppLoger.showMsgError("SET_GEO_PROGRESS", "DISTANCE_  " + seekDistance);
//                    seekBar.setProgress(seekDistance * 2);
//
//                    progressContainer.setY(seekBarWrapper.getHeight() -
//                            (seekBar.getThumbCenter() + UtilsApp.dp2px(15)));
//                    calculatedRadius = seekBar.getProgress() + minRadiusInMeters;
//
//                    String strProgress;
//                    if (isRadiusInDefaultUnit) {
//                        if ((calculatedRadius >= _1_KM)) {
//                            if (calculatedRadius % _1_KM == 0) {
//                                strProgress = String.format(Locale.ENGLISH, "%d", (int) (calculatedRadius / (float) _1_KM)) + " KM";
//                            } else {
//                                strProgress = String.format(Locale.ENGLISH, "%.02f", (calculatedRadius / (float) _1_KM)) + " KM";
//                            }
//                        } else {
//                            strProgress = calculatedRadius + " m";
//                        }
//                    } else {
//                        strProgress = String.format(Locale.ENGLISH, "%.02f", meters2Miles(calculatedRadius)) + " MILE";
//                    }
//                    tvProgress.setText(strProgress);
//                }
//
//            }
//        } catch (Exception ex) {
//            AppLoger.showMsgError(TAG, "" + ex.getMessage());
//        }
//    }
//
//
//   /* private void setPlaceFormLatLongFromGoogle(double latitude, double longitude) {
//        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//
//        try {
//            Observable<Address> objectObservable = Observable.create((ObservableEmitter<Address> subscriber) -> {
//                try {
//                    List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//                    Address       address   = addresses.get(0);
//
//                    subscriber.onNext(address);
//                } catch (Exception ex) {
//                    ex.printStackTrace();
//                }
//            }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//
//            Disposable subscribe = objectObservable.subscribe(address -> {
//                try {
//                    if (address != null) {
//                        if (oldPlace == null) {
//                            oldPlace = new com.camtrack.tracker.entities.Place();
//                            oldPlace.setId(System.currentTimeMillis());
//                            oldPlace.setRadius(defaultRadius);
//                        }
//
//                        oldPlace.setName(edtNameOfPlace.getText().toString());
//                        oldPlace.setLatitude(latitude);
//                        oldPlace.setLongitude(longitude);
//                        oldPlace.setUpdatedOn(System.currentTimeMillis());
//                        if (TextUtils.isEmpty(address.getPremises())) {
//                            oldPlace.setAddress1(address.getFeatureName());
//                        } else {
//                            oldPlace.setAddress1(address.getPremises());
//                        }
//                        oldPlace.setAddress2(address.getAddressLine(0));
//
//                        if (chBoxIn.isChecked() && chBoxOut.isChecked()) {
//                            oldPlace.setType(POIType.fromValue(POIType.BOTH));
//                            oldPlace.setEnabled(true);
//                        } else if (chBoxIn.isChecked()) {
//                            oldPlace.setType(POIType.fromValue(POIType.ENTRY));
//                            oldPlace.setEnabled(true);
//                        } else if (chBoxOut.isChecked()) {
//                            oldPlace.setType(POIType.fromValue(POIType.EXIT));
//                            oldPlace.setEnabled(true);
//                        } else {
//                            oldPlace.setType("");
//                            oldPlace.setEnabled(false);
//                        }
//
//                        resetUI(oldPlace);
//                        setMarkerAndCircle(oldPlace);
//                    }
//                } catch (Exception exception) {
//                    exception.printStackTrace();
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }*/
//
//    private void resetUI(com.camtrack.tracker.entities.Place place) {
//        rlPoiDetails.setVisibility(View.VISIBLE);
//        seekBarWrapper.setVisibility(View.VISIBLE);
////        btnSaveLocation.setVisibility(View.VISIBLE);
//
//        txtSearchResult.setText(place.getAddress1() + ", " + place.getAddress2());
//
//        if (!TextUtils.isEmpty(place.getName())) {
//            edtNameOfPlace.setText(place.getName());
//            edtNameOfPlace.setSelection(edtNameOfPlace.getText().length());
//        }
//
//        if (TextUtils.isEmpty(place.getType())) {
//            chBoxIn.setOn(false);
//            chBoxOut.setOn(false);
//        } else {
//            if (POIType.fromValue(POIType.BOTH).equalsIgnoreCase(place.getType())) {
//                chBoxIn.setOn(true);
//                chBoxOut.setOn(true);
//            } else if (POIType.fromValue(POIType.ENTRY).equalsIgnoreCase(place.getType())) {
//                chBoxIn.setOn(true);
//                chBoxOut.setOn(false);
//            } else if (POIType.fromValue(POIType.EXIT).equalsIgnoreCase(place.getType())) {
//                chBoxIn.setOn(false);
//                chBoxOut.setOn(true);
//            }
//        }
//    }
//
//    @SuppressWarnings({"MissingPermission"})
//    private void enableLocationComponent() {
//        // Check if permissions are enabled and if not request
//        if (PermissionsManager.areLocationPermissionsGranted(this)) {
//
//            LocationComponent locationComponent = googleMap.getLocationComponent();
//            locationComponent.activateLocationComponent(this, googleMap.getStyle());
//            locationComponent.setLocationComponentEnabled(false);
//            // Set the component's camera mode
//            locationComponent.setCameraMode(CameraMode.TRACKING);
//
//            Location originLocation = locationComponent.getLastKnownLocation();
//            if (originLocation == null) {
//                originLocation = new Location("network");
//            }
//            double bestLat = 0, bestLng = 0;
//
//            if (oldPlace != null) {
//                bestLat = oldPlace.getLatitude();
//                bestLng = oldPlace.getLongitude();
//            }
//
//            //Get lat, long from history(device lat, long)
//            if (bestLat == 0 && bestLng == 0) {
//                RealDataInterfaceHelper realDataInterfaceHelper = RealDataInterfaceHelper.getInstance();
//                TripHistoryData         tripHistoryData         = realDataInterfaceHelper.getTripHistry(KentSecurityPrefs.getString(this, Constants.DEVICE_SERIALNUMBER));
//                if (tripHistoryData != null) {
//                    bestLat = tripHistoryData.getLatitude();
//                    bestLng = tripHistoryData.getLongitude();
//                }
//            }
//
//            // If still lat, long not found from history check form local preferences
//            if (bestLat == 0 && bestLng == 0) {
//                if (!TextUtils.isEmpty(KentSecurityPrefs.getString(POIDetailsActivity.this, "lat")) && Double.parseDouble(KentSecurityPrefs.getString(POIDetailsActivity.this, "lat")) > 0) {
//                    try {
//                        bestLat = Double.parseDouble(KentSecurityPrefs.getString(this, "lat"));
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        bestLng = Double.parseDouble(KentSecurityPrefs.getString(this, "lng"));
//                    } catch (NumberFormatException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            // If still lat, long not found get current location
//            if (bestLat == 0 && bestLng == 0) {
//                bestLat = originLocation.getLatitude();
//                bestLng = originLocation.getLongitude();
//            }
//
//            // If still lat, long not found show India map
//            if (bestLat == 0 && bestLng == 0) {
//                bestLat = 28.644800;
//                bestLng = 77.216721;
//            }
//
//            currentLocation = new LatLng(bestLat, bestLng);
//            //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 14.0f));
//            createLayer(currentLocation);
//            setZoomLevel(currentLocation.getLatitude(), currentLocation.getLongitude());
//            setPlaceFormLatLongFromMapBox(currentLocation.getLatitude(), currentLocation.getLongitude());
//        } else {
//            requestLocationPermission();
//        }
//    }
//
//    private void requestLocationPermission() {
//        permissionsManager = new PermissionsManager(this);
//        permissionsManager.requestLocationPermissions(this);
//    }
//
//    @Override
//    public void onExplanationNeeded(List<String> permissionsToExplain) {
////        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void onPermissionResult(boolean granted) {
//        if (granted) {
//            enableLocationComponent();
//        } else {
//            Toast.makeText(this, R.string.permission_required_toast, Toast.LENGTH_LONG).show();
//            finish();
//        }
//    }
//
//    private void enableMyLocationOnMap() {
//        double latitude = 0, longitude = 0;
//        if (UtilsApp.isLocationEnabled(POIDetailsActivity.this)) {
//            if (ContextCompat.checkSelfPermission(POIDetailsActivity.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//
////                googleMap.setMyLocationEnabled(true);
////                Location location = googleMap.getMyLocation();
//                Location location = null;
//                if (location != null) {
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 14.0f));
//                }
//
//                RealDataInterfaceHelper realDataInterfaceHelper = RealDataInterfaceHelper.getInstance();
//                TripHistoryData         tripHistoryData         = realDataInterfaceHelper.getTripHistry(KentSecurityPrefs.getString(this, Constants.DEVICE_SERIALNUMBER));
//                if (tripHistoryData != null) {
//                    latitude = tripHistoryData.getLatitude();
//                    longitude = tripHistoryData.getLongitude();
//
//                }
//
//                // If still lat, long not found from history check form local preferences
//                if (latitude == 0 && longitude == 0) {
//                    if (!TextUtils.isEmpty(KentSecurityPrefs.getString(POIDetailsActivity.this, "lat")) && Double.parseDouble(KentSecurityPrefs.getString(POIDetailsActivity.this, "lat")) > 0) {
//                        latitude = Double.parseDouble(KentSecurityPrefs.getString(this, "lat"));
//                        longitude = Double.parseDouble(KentSecurityPrefs.getString(this, "lng"));
//                    }
//                }
//
//                // If still lat, long not found show India map
//                if (latitude == 0 && longitude == 0) {
//                    latitude = 28.644800;
//                    longitude = 77.216721;
//                }
//
//                LatLng india = new LatLng(latitude, longitude);
//                googleMap.moveCamera(CameraUpdateFactory.newLatLng(india));
//                setZoomLevel(latitude, longitude);
//            }
//        } else {
//            latitude = 28.644800;
//            longitude = 77.216721;
//
//            LatLng india = new LatLng(latitude, longitude);
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(india));
//            setZoomLevel(latitude, longitude);
//        }
//    }
//
//    private void setMarkerAndCircle(com.camtrack.tracker.entities.Place place) {
//        if (null != place) {
//            createLayer(dragLocation != null ? dragLocation : currentLocation, true);
//        }
//    }
//
//    private void setDefaultZoom(double latitude, double longitude) {
//        if (oldPlace != null) {
//            oldPlace.setRadius(100);
//        }
//        setZoomLevel(latitude, longitude);
//    }
//
//    private void setZoomLevel(double latitude, double longitude) {
//        if (googleMap != null) {
//            LatLng latLng = new LatLng(latitude, longitude);
//
//            int zoomLevel = 14;
//            if (oldPlace != null) {
//                AppLoger.showMsgError("Inner Radius", "" + oldPlace.getRadius());
//                double radius = oldPlace.getRadius() * 2;
//                double scale  = radius / 500;
//                zoomLevel = (int) (16 - Math.log(scale) / Math.log(2));
//            }
//            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
//        }
//    }
//
//    private float meters2Miles(int meter) {
//        return (meter / 1609.34f);
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        mapView.onPause();
//        hideKeyboard();
//    }
//
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
//
//    @Override
//    protected void onDestroy() {
//        mapView.onDestroy();
//        if (googleLocClient != null) {
//            googleLocClient.onDisconnect();
//        }
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        mapView.onSaveInstanceState(outState);
//    }
//
//    @SuppressLint("WrongConstant")
//    private OnMapReadyCallback onMapReadyCallback = new OnMapReadyCallback() {
//        @Override
//        public void onMapReady(@NonNull MapboxMap mapboxMap) {
//            googleMap = mapboxMap;
//
//            googleMap.setStyle(Constants.MAP_CUSTOM_VIEW, style -> {
//                enableLocationComponent();
//            });
//
//            setUiSetting();
//
//            googleMap.addOnCameraIdleListener(() -> {
//                try {
//                    if (isCameraMoveStarted) {
//                        handler.removeCallbacksAndMessages(null);
//                        handler.postDelayed(() -> {
//                            txtSearchResult.setText("");
//                            LatLng latLng = googleMap.getCameraPosition().target;
//                            setPlaceFormLatLongFromMapBox(latLng.getLatitude(), latLng.getLongitude());
//                            dragLocation = latLng;
//                            isCameraMoveStarted = false;
//                        }, 100);
//                    }
//                }catch (Exception ex){
//                    ex.printStackTrace();
//                }
//            });
//
//            googleMap.addOnCameraMoveStartedListener(reason -> {
//                if (reason == MapboxMap.OnCameraMoveStartedListener.REASON_API_GESTURE) {
//                    handler.removeCallbacksAndMessages(null);
//                    handler.postDelayed(() -> {
//                        isCameraMoveStarted = true;
//                        txtSearchResult.setText("Loading...");
//                    }, 100);
//                }
//            });
//        }
//    };
//
//    private void deletePOIAlert() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(POIDetailsActivity.this);
//        builder.setTitle("Delete Place");
//        builder.setMessage("Are you sure you want to delete Place of Interest?")
//                .setCancelable(false)
//                .setPositiveButton("OK", (dialog, id) -> deletePOI())
//                .setNegativeButton("Cancel", (dialog, id) -> dialog.cancel());
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//
//    private void deletePOI() {
//        try {
//            String      deviceSerialNumber = KentSecurityPrefs.getString(POIDetailsActivity.this, Constants.DEVICE_SERIALNUMBER);
//            RealmUserV2 deviceUser         = RealmUserV2.getInstance(deviceSerialNumber);
//            Realm       realm              = deviceUser.getRealm();
//
//            if (realm != null) {
//                int                                               position = -1;
//                RealmResults<com.camtrack.tracker.entities.Place> poiList  = realm.where(com.camtrack.tracker.entities.Place.class).findAll();
//
//                if (poiList != null && poiList.size() > 0) {
//                    for (int i = 0; i < poiList.size(); i++) {
//                        com.camtrack.tracker.entities.Place place = poiList.get(i);
//                        if (place != null && place.isValid() && oldPlace != null) {
//                            if (place.getId() == oldPlace.getId()) {
//                                position = i;
//                                break;
//                            }
//                        }
//                    }
//
//                    if (position != -1) {
//                        realm.beginTransaction();
//                        poiList.deleteFromRealm(position);
//                        realm.commitTransaction();
//
//                        handler.postDelayed(() -> {
//                            Intent intent = new Intent(POIListActivity.ACTION_REFRESH_POILIST);
//                            LocalBroadcastManager.getInstance(POIDetailsActivity.this).sendBroadcast(intent);
//                            POIDetailsActivity.this.finish();
//                        }, 320);
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void uiWhenPoiUpdate() {
//        showDeletePOIMenuItem = true;
//        editImageView.setVisibility(View.VISIBLE);
//        invalidateOptionsMenu();
//        btnSaveLocation.setVisibility(View.GONE);
//    }
//
//    private void uiWhenPoiAdd(){
//        showDeletePOIMenuItem = false;
//        editImageView.setVisibility(View.GONE);
//        invalidateOptionsMenu();
//        btnSaveLocation.setVisibility(View.VISIBLE);
//    }
//
//    private void uiWhenPoiEditable(){
//        showDeletePOIMenuItem = false;
//        invalidateOptionsMenu();
//        btnSaveLocation.setVisibility(View.VISIBLE);
//    }
//
//    private void disableTouch(){
//        if(googleMap!=null){
//            googleMap.getUiSettings().setScrollGesturesEnabled(false);
//            googleMap.getUiSettings().setZoomGesturesEnabled(false);
//        }
//        vh_search.setClickable(false);
//        seekBar.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
//    }
//
//    private void enableTouch(){
//        if(googleMap!=null){
//            googleMap.getUiSettings().setScrollGesturesEnabled(true);
//            googleMap.getUiSettings().setZoomGesturesEnabled(true);
//        }
//        vh_search.setClickable(true);
//        seekBar.setOnTouchListener(null);
//    }
//
//
//    private void setSoftKeyPad(){
//        vh_poi_holder.getViewTreeObserver().addOnGlobalLayoutListener(
//                new ViewTreeObserver.OnGlobalLayoutListener() {
//                    @Override
//                    public void onGlobalLayout() {
//                        try {
//                            Rect r = new Rect();
//                            vh_poi_holder.getWindowVisibleDisplayFrame(r);
//                            int screenHeight = vh_poi_holder.getRootView().getHeight();
//
//                            // r.bottom is the position above soft keypad or device button.
//                            // if keypad is shown, the r.bottom is smaller than that before.
//                            int keypadHeight = screenHeight - r.bottom;
//
//
//                            if (keypadHeight > screenHeight * 0.15) { // 0.15 ratio is perhaps enough to determine keypad height.
//                                // keyboard is opened
//                                disableTouch();
//                            } else {
//                                // Keypad is closed
//                                enableTouch();
//                            }
//                        }catch (Exception ex){
//                            ex.printStackTrace();
//                        }
//                    }
//                });
//    }
//
//}