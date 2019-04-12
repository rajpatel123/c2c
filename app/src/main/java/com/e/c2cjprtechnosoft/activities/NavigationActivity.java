package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.fragments.BookYourRideFragment;
import com.e.c2cjprtechnosoft.fragments.KnowYourRideFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textViewprofile;
    NavigationView navigationView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationView = findViewById(R.id.nav_view);
        View viewHeader = navigationView.inflateHeaderView(R.layout.nav_header_navigation);
        relativeLayout = viewHeader.findViewById(R.id.relative_top);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(NavigationActivity.this, "Hello", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NavigationActivity.this, ProfileActivity.class));
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.navigation_drawer_icon);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.framecontainer, new BookYourRideFragment())
                .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.payment) {
            Intent intent = new Intent(NavigationActivity.this, PaymentActivity.class);
            startActivity(intent);

        } else if (id == R.id.your_rider) {
            Intent intent = new Intent(NavigationActivity.this, MyRidesActivity.class);
            startActivity(intent);

        } else if (id == R.id.free_rider) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framecontainer, new KnowYourRideFragment())
                    .commit();

        } else if (id == R.id.notifications) {
            Intent intent = new Intent(NavigationActivity.this, NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.settings) {

            Intent intent = new Intent(NavigationActivity.this, SettingActivity.class);
            startActivity(intent);

        } else if (id == R.id.helps) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
