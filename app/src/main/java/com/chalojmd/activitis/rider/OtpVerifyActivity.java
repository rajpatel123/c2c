package com.chalojmd.activitis.rider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chalojmd.R;

public class OtpVerifyActivity extends AppCompatActivity {
    private ImageView imageView,imageView0,imageView1,imageView2,imageView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        imageView = findViewById(R.id.next_button2);
        imageView0= findViewById(R.id.drivingLicence0);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( OtpVerifyActivity.this,DriverLicenseActivity.class);
                startActivity(i);
            }
        });
    }


}
