package com.chalojmd.activitis.rider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chalojmd.R;

public class VichelDetailActivity extends AppCompatActivity {
    private ImageView imageView,imageViewVicheal;
    private AnimatorSet animatorSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vichel_detail);
        imageView = findViewById(R.id.next_button4);
        imageViewVicheal=findViewById(R.id.vichiel_imageview);

        imageViewVicheal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // animatorSet= (AnimatorSet) AnimatorInflater.loadAnimator(VichelDetailActivity.this,R.animator.animation_horizontal_right_in);
               // animatorSet.setTarget(imageViewVicheal);
                long l =1 ;
                animatorSet.setDuration(l);
                animatorSet.start();

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( VichelDetailActivity.this,RegistrationCompleteActivity.class);
                startActivity(i);
            }
        });
    }
}
