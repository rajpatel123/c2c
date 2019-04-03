package com.e.c2cjprtechnosoft.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneNumberSubmitActivity extends AppCompatActivity  {

    @BindView(R.id.next_button)
    Button nextBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_submit);
        ButterKnife.bind(this);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PhoneNumberSubmitActivity.this, "Submit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
