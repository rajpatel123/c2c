package com.chalojmd.activitis.rider;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chalojmd.R;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.Utils.C2CPref;
import com.chalojmd.Utils.Utils;
import com.chalojmd.model.otpverify.VerifyNewOtpRequest;
import com.chalojmd.model.otpverify.VerifyNewOtpResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerifyActivity extends AppCompatActivity {
    private ImageView imageView,backButton_image;
    private EditText otpEditText;
    String mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        imageView = findViewById(R.id.next_button2);
        backButton_image=findViewById(R.id.otp_back);
        otpEditText = findViewById(R.id.pin_first_edittext);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateverifyOtp();
            }
        });

        backButton_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent i =new Intent(OtpVerifyActivity.this,LoginRegisterActivity.class );
                 startActivity(i);
            }
        });
    }

    public void validateverifyOtp() {
        boolean check = true;

        String Usermobilenumber = C2CPref.getString(getApplicationContext(), "mobilenumber");
        String otpNumber = otpEditText.getText().toString().trim();

        if (otpNumber.isEmpty()) {
            otpEditText.setError("enter a valid otp");
            check = false;
        } else {
            otpEditText.setError(null);
        }


        if (check) {
            if (Usermobilenumber != null) {
                VerifyNewOtpRequest verifyNewOtpRequest = new VerifyNewOtpRequest();

                verifyNewOtpRequest.setOtp(otpNumber);
                verifyNewOtpRequest.setMobile(Usermobilenumber);
                Utils.showProgressDialog(this);
                RestClient.verifyByOtp(verifyNewOtpRequest, new Callback<VerifyNewOtpResponse>() {
                    @Override
                    public void onResponse(Call<VerifyNewOtpResponse> call, Response<VerifyNewOtpResponse> response) {
                        Utils.dismissProgressDialog();
                        if (response != null) {
                            if (response.body().getStatus() == true) {
                                Intent i = new Intent(OtpVerifyActivity.this, RegistrationFormActivity.class);
                                Toast.makeText(OtpVerifyActivity.this, " otp verified successfully", Toast.LENGTH_SHORT).show();
                                startActivity(i);
                                finish();

                            }else if (response.body().getStatus() == false) {
                                Toast.makeText(OtpVerifyActivity.this, " please enter  valid otp", Toast.LENGTH_SHORT).show();

                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<VerifyNewOtpResponse> call, Throwable t) {
                        Toast.makeText(OtpVerifyActivity.this, "failure", Toast.LENGTH_SHORT).show();

                    }
                });


            }


        }


    }


}
