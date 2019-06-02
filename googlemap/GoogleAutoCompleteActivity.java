package com.camtrack.tracker.googlemap;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.camtrack.tracker.R;

import java.util.ArrayList;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vrajput on 12/19/2018.
 */

public class GoogleAutoCompleteActivity extends AppCompatActivity {

    public static final String TAG = GoogleAutoCompleteActivity.class.getSimpleName();
    private static final String BASE_URL = "https://maps.googleapis.com/"; // place/autocomplete/json
    private ListView listView;
    private GoogleLocationAdapter adapter;
    private SearchView searchView;
    private GoogleApiInterface googleApiInterface;

    public static void startSearchScreen(Context context) {
        Intent i = new Intent();
        i.setClass(context, GoogleAutoCompleteActivity.class);
        context.startActivity(i);
    }


    public static void startSearchScreenForResult(Activity context, String title, int code) {
        Intent intent = new Intent(context, GoogleAutoCompleteActivity.class);
        intent.putExtra("title", title);
        context.startActivityForResult(intent, code);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_auto_complete);

        Toolbar toolbar = (Toolbar) findViewById(R.id.prod_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            if (getIntent().hasExtra("title")) {
                String title = getIntent().getStringExtra("title");
                getSupportActionBar().setTitle(title);
            } else {
                getSupportActionBar().setTitle(R.string.geo_fence);
            }
        }

        getRetrofitInstance();
        initSearchView();
        setInitViews();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setInitViews() {
        ArrayList<Place> placeList = new ArrayList<>();

        listView = findViewById(R.id.search_place);
        adapter = new GoogleLocationAdapter(this, placeList, googleApiInterface);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = adapter.getMyAddress(position);
                sendData(place);
            }
        });
    }

    private void sendData(Place place) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", place);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void initSearchView() {
        searchView = findViewById(R.id.searchView);
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.length() > 1) {
                    adapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();
                return true;
            }
        });
    }


    public GoogleApiInterface getRetrofitInstance() {
        if (googleApiInterface == null) {
            Retrofit retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            googleApiInterface = retrofit.create(GoogleApiInterface.class);
        }
        return googleApiInterface;
    }

}