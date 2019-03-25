package com.e.c2cjprtechnosoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.e.c2cjprtechnosoft.R;

public class DropSearchActivity extends AppCompatActivity {

    ImageView imageViewBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drop_search);
        imageViewBack=findViewById(R.id.back1);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
