package com.e.c2cjprtechnosoft.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.e.c2cjprtechnosoft.R;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
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
