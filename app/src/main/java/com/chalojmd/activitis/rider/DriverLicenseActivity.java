package com.chalojmd.activitis.rider;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chalojmd.R;

public class DriverLicenseActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageView0,imageView1,imageView2,imageView3;
 private AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_license);
        imageView0= findViewById(R.id.drivingLicence0);

        imageView = findViewById(R.id.next_button3);
        imageView0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // animatorSet= (AnimatorSet) AnimatorInflater.loadAnimator(DriverLicenseActivity.this,R.animator.animation_horizontal_right_in);
                animatorSet.setTarget(imageView0);
                animatorSet.start();
            }
        });

         imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( DriverLicenseActivity.this,VichelDetailActivity.class);
                startActivity(i);
            }
         });
    }
}
