package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.graphics.Color;
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
import android.widget.TextView;

import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.fragments.AboutFragment;
import com.e.c2cjprtechnosoft.fragments.BookYourRideFragment;
import com.e.c2cjprtechnosoft.fragments.C2CmoneyFragment;
import com.e.c2cjprtechnosoft.fragments.KnowYourRideFragment;
import com.e.c2cjprtechnosoft.fragments.PaymentFragment;
import com.e.c2cjprtechnosoft.fragments.RateCardFragment;
import com.e.c2cjprtechnosoft.fragments.SupportFragment;
import com.e.c2cjprtechnosoft.fragments.Your_Rider_Fragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView textViewprofile;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        navigationView = findViewById(R.id.nav_view);
        View viewHeader = navigationView.inflateHeaderView(R.layout.nav_header_navigation);
        textViewprofile = viewHeader.findViewById(R.id.myprofile);
        textViewprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

        if (id == R.id.book_your_ride) {

           /*Intent intent=new Intent(NavigationActivity.this,MapsActivity.class);
           startActivity(intent);
*/
            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.framecontainer, new BookYourRideFragment())
                    .commit();
        } else if (id == R.id.your_rider) {

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.framecontainer, new Your_Rider_Fragment())
                    .commit();

        } else if (id == R.id.know_your_ride) {

            getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new KnowYourRideFragment()).commit();

        } else if (id == R.id.mygroup) {

        } else if (id == R.id.Refer_earn) {

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
           /* String shareBody = "Here is the share content body";
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);*/
            startActivity(Intent.createChooser(sharingIntent, "Share via"));

        } else if (id == R.id.myoffers) {

        } /*else if (id == R.id.rate_card) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, new RateCardFragment())
                    .addToBackStack(null).commit();

        } else if (id == R.id.c2c_money) {

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.framecontainer, new C2CmoneyFragment())
                    .addToBackStack(null).commit();

        } else if (id == R.id.payment) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, new PaymentFragment())
                    .addToBackStack(null).commit();

        } else if (id == R.id.support) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, new SupportFragment())
                    .addToBackStack(null).commit();

        } else if (id == R.id.about) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer, new AboutFragment())
                    .addToBackStack(null).commit();

        }
*/
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
