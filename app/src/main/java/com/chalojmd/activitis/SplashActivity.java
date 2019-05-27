package com.chalojmd.activitis;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chalojmd.R;
import com.chalojmd.Utils.C2CPref;
import com.chalojmd.Utils.Constants;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (C2CPref.getBoolean(SplashActivity.this, Constants.IS_LOOGED_IN)){
                    Intent i = new Intent(SplashActivity.this, NavigationActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(SplashActivity.this, PhoneLoginActivity.class);
                    startActivity(i);
                    finish();
                }

            }
        }, SPLASH_TIME_OUT);
    }
}

