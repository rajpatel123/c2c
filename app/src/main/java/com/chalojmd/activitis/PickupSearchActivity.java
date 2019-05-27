package com.chalojmd.activitis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;

import com.chalojmd.R;

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
