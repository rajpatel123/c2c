package com.e.c2cjprtechnosoft.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.e.c2cjprtechnosoft.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.verifyBtn)
    Button verifyButton;

    @BindView(R.id.crossBtn)
    ImageView crossBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        ButterKnife.bind(this);
        verifyButton.setOnClickListener(this);
        crossBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.verifyBtn:
                verify();
                break;

            case R.id.crossBtn:
                finish();
                break;
        }
    }

    private void verify() {
        Intent intent=new Intent(VerifyOtpActivity.this,AllowPermissionActivity.class);
        startActivity(intent);
        finish();

    }
}
