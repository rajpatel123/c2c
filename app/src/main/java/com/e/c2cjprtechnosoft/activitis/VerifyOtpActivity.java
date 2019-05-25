package com.e.c2cjprtechnosoft.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.Activities.AllowPermissionActivity;
import com.e.c2cjprtechnosoft.Activities.NavigationActivity;
import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.Retrofit.RestClient;
import com.e.c2cjprtechnosoft.Utils.Utils;
import com.e.c2cjprtechnosoft.model.verifyOtp.VerifyOTPResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.verifyBtn)
    Button verifyButton;

    @BindView(R.id.crossBtn)
    ImageView crossBtn;


    @BindView(R.id.verify_otp)
    EditText editTextVerifyOtp;
    String otp, mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        ButterKnife.bind(this);
        verifyButton.setOnClickListener(this);
        crossBtn.setOnClickListener(this);
        if (getIntent().hasExtra("MOBILE")) {
            mobile = getIntent().getStringExtra("MOBILE");
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.verifyBtn:
                verifyOtp();
                break;

            case R.id.crossBtn:
                finish();
                break;
        }
    }

    private void verifyOtp() {
        otp = editTextVerifyOtp.getText().toString().trim();
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.verifyOtp(otp, mobile, new Callback<VerifyOTPResponse>() {
                @Override
                public void onResponse(Call<VerifyOTPResponse> call, Response<VerifyOTPResponse> response) {

                    if (response.body() != null) {
                        if (response.body().getUsername() == null) {
                            Intent intent = new Intent(VerifyOtpActivity.this, UpdateUserActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(VerifyOtpActivity.this, NavigationActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }

                @Override
                public void onFailure(Call<VerifyOTPResponse> call, Throwable t) {

                    Toast.makeText(VerifyOtpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    Utils.dismissProgressDialog();
                }
            });
        } else {
            Toast.makeText(this, "Internet Connections Failed", Toast.LENGTH_SHORT).show();
            Utils.dismissProgressDialog();

        }


    }
    }

