package com.e.c2cjprtechnosoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.e.c2cjprtechnosoft.R;

import java.util.ArrayList;
import java.util.Arrays;

public class PickupSearchActivity extends AppCompatActivity {

    ImageView imageViewBack;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_search);
        imageViewBack=findViewById(R.id.back);
        searchView=findViewById(R.id.search_view);




    }
}
