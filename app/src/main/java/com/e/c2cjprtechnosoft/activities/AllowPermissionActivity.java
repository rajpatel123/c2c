package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllowPermissionActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.allowBtn)
    Button allowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow_permission);
        ButterKnife.bind(this);
        allowButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.allowBtn:
                allowPermission();

        }

    }

    private void allowPermission() {
        Intent intent = new Intent(AllowPermissionActivity.this, GPSPermissionActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Permission Access", Toast.LENGTH_SHORT).show();
    }
}
