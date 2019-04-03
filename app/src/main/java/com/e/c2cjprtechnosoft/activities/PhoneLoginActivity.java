package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhoneLoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.phone_number)
    LinearLayout linearLayoutPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);
        ButterKnife.bind(this);
      linearLayoutPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.phone_number:
                startActivity(new Intent(PhoneLoginActivity.this, PhoneNumberSubmitActivity.class));
                break;

        }

    }
}
