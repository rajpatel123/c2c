package com.chalojmd.activitis.rider;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.chalojmd.R;
import com.chalojmd.Retrofit.RestClient;
import com.chalojmd.model.otpverify.VerifyOtpRequest;
import com.chalojmd.model.otpverify.VerifyOtpResponse;
import com.chalojmd.model.verifyOtp.VerifyOTPResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpVerifyActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText otpEditText;
    String Mobile = "9205089335";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        imageView = findViewById(R.id.next_button2);
        otpEditText = findViewById(R.id.pin_first_edittext);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateverifyOtp();
            }
        });
    }
    public  void  validateverifyOtp(){
        boolean check = true;
        String otpNumber = otpEditText.getText().toString().trim();
        if (otpNumber.isEmpty() || otpEditText.length() < 4 || otpEditText.length() > 10) {
            otpEditText.setError("enter a valid otp");
            check = false;
        } else {
            otpEditText.setError(null);
        }
        if(check){

            VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
            verifyOtpRequest.setMobile(Mobile);
            verifyOtpRequest.setOtp(otpNumber);

            RestClient.verifyByOtp(verifyOtpRequest, new Callback<VerifyOtpResponse>() {
                @Override
                public void onResponse(Call<VerifyOtpResponse> call, Response<VerifyOtpResponse> response) {

                    if(response !=null){
                        if(response.body().getStatus() == true){
                            Intent i = new Intent(OtpVerifyActivity.this, RegistrationCompleteActivity.class);
                            startActivity(i);

                        }


                    }

                }

                @Override
                public void onFailure(Call<VerifyOtpResponse> call, Throwable t) {
                    Toast.makeText(OtpVerifyActivity.this, "failure", Toast.LENGTH_SHORT).show();

                }
            });






        }




    }


}
