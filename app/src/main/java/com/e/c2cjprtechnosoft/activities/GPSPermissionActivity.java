package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GPSPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.turnOnGPSBtn)
    Button turnOnBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpspermission);
        ButterKnife.bind(this);
        turnOnBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.turnOnGPSBtn:
                turnOnGPS();
        }

    }

    private void turnOnGPS() {

        Intent intent = new Intent(GPSPermissionActivity.this, NavigationActivity.class);
        startActivity(intent);
    }
}
