package com.chalojmd.activitis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.chalojmd.R;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.Utils.Constants;
import com.chalojmd.Utils.Utils;
import com.chalojmd.model.verifyMobile.VerifyMobileResponse;
import com.chalojmd.model.verifyOtp.VerifyOTPResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VerifyOtpActivity extends AppCompatActivity {

    @BindView(R.id.backButton)
    AppCompatImageView crossBtn;

    @BindView(R.id.otpTxt)
    TextView otpTxt;

    @BindView(R.id.textview_mobile)
    TextView textview_mobile;

    @BindView(R.id.pin_layout)
    PinEntryEditText pinLayout;

    @BindView(R.id.resendOtp)
    TextView resendOtp;

    @BindView(R.id.pin_content_layout)
    RelativeLayout pinContentLayout;


    boolean isSocial;
    boolean isPhone;

    private final int COUNT_TIMER = 60 * 1000;
    private final int COUNT_INTERVAL = 1000;
    private String user_id;
    private String phone;
    private String mobile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        ButterKnife.bind(this);


        SpannableString spannableString = new SpannableString(resendOtp.getText().toString());
        spannableString.setSpan(new UnderlineSpan(), 0, resendOtp.getText().toString().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        resendOtp.setText(spannableString);


        Intent intent = getIntent();

        user_id = intent.getStringExtra("user_id");
        phone = intent.getStringExtra("mobile");

        if (getIntent().hasExtra("MOBILE")) {
            mobile = getIntent().getStringExtra("MOBILE");

            textview_mobile.setText("+91 - "+mobile);
        }
        mobile = "8285886155";
        openKeyboard();

        pinLayout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (pinLayout.getText().toString().trim().length() >= 6) {
                    verifyOtp(pinLayout.getText().toString().trim());
                }
            }


        });

    }



    @OnClick(R.id.backButton)
    public void onCrossBtnClick() { finish();
    }


    @OnClick(R.id.resendOtp)
    public void reesedOTP() {
        resendOtp();
    }


    @OnClick(R.id.verifyBtn)
    public void onVerifyOTP() {
        if (pinLayout.getText().toString().length() >= 6) {
            verifyOtp(pinLayout.getText().toString());
        } else {
            Utils.displayToast(this, "Please enter OTP");
        }
    }

    private void verifyOtp(String otp) {
        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.verifyOtp(otp, mobile, new Callback<VerifyOTPResponse>() {
                @Override
                public void onResponse(Call<VerifyOTPResponse> call, Response<VerifyOTPResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getUsername() == null) {
                            Intent intent = new Intent(VerifyOtpActivity.this, UpdateUserActivity.class);
                            intent.putExtra("mobile",mobile);
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
    @OnClick(R.id.resendOtp)
    public void resendOtp() {
        if (resendOtp.isClickable()) {
            Utils.showProgressDialog(this);
            RestClient.verifyMobile(mobile, new Callback<VerifyMobileResponse>() {
                @Override
                public void onResponse(Call<VerifyMobileResponse> call, Response<VerifyMobileResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.code()== Constants.SUCCESS){
                        if (!TextUtils.isEmpty(response.body().getOtp())){
                          Utils.displayToast(VerifyOtpActivity.this,"Otp send successfully");
                        }
                    }
                }

                @Override
                public void onFailure(Call<VerifyMobileResponse> call, Throwable t) {
                    Toast.makeText(VerifyOtpActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }



    private void openKeyboard() {
        new Handler().postDelayed(() -> {
            pinLayout.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, 0, 0, 0));
            pinLayout.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, 0, 0, 0));
        }, 100);

    }



    // Hide soft keyboard
    private void hideKeyboard(View view) {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null && view != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}


//
//import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.chalojmd.activitis.AllowPermissionActivity;
//import com.chalojmd.activitis.NavigationActivity;
//import com.chalojmd.R;
//import com.chalojmd.Retrofit.RestClient;
//import com.chalojmd.Utils.Utils;
//import com.chalojmd.model.verifyOtp.VerifyOTPResponse;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;

//public class VerifyOtpActivity extends AppCompatActivity implements View.OnClickListener {
//
//    @BindView(R.id.verifyBtn)
//    Button verifyButton;
//
//    @BindView(R.id.crossBtn)
//    ImageView crossBtn;
//
//
//    @BindView(R.id.verify_otp)
//    EditText editTextVerifyOtp;
//    String otp, mobile;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_verify_otp);
//        ButterKnife.bind(this);
//        verifyButton.setOnClickListener(this);
//        crossBtn.setOnClickListener(this);
//        if (getIntent().hasExtra("MOBILE")) {
//            mobile = getIntent().getStringExtra("MOBILE");
//        }
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.verifyBtn:
//                verifyOtp();
//                break;
//
//            case R.id.crossBtn:
//                finish();
//                break;
//        }
//    }
//
//    private void verifyOtp() {
//        otp = editTextVerifyOtp.getText().toString().trim();
//        if (Utils.isInternetConnected(this)) {
//            Utils.showProgressDialog(this);
//            RestClient.verifyOtp(otp, mobile, new Callback<VerifyOTPResponse>() {
//                @Override
//                public void onResponse(Call<VerifyOTPResponse> call, Response<VerifyOTPResponse> response) {
//
//                    if (response.body() != null) {
//                        if (response.body().getUsername() == null) {
//                            Intent intent = new Intent(VerifyOtpActivity.this, UpdateUserActivity.class);
//                            startActivity(intent);
//                            finish();
//
//                        } else {
//                            Intent intent = new Intent(VerifyOtpActivity.this, NavigationActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<VerifyOTPResponse> call, Throwable t) {
//
//                    Toast.makeText(VerifyOtpActivity.this, "Failed", Toast.LENGTH_SHORT).show();
//                    Utils.dismissProgressDialog();
//                }
//            });
//        } else {
//            Toast.makeText(this, "Internet Connections Failed", Toast.LENGTH_SHORT).show();
//            Utils.dismissProgressDialog();
//
//        }
//
//
//    }
//    }
//
