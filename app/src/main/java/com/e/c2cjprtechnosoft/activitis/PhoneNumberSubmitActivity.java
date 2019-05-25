package com.e.c2cjprtechnosoft.activitis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.c2cjprtechnosoft.R;
import com.e.c2cjprtechnosoft.Retrofit.RestClient;
import com.e.c2cjprtechnosoft.Utils.Utils;
import com.e.c2cjprtechnosoft.model.verifyMobile.VerifyMobileResponse;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberSubmitActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.next_button)
    Button nextBtn;
    @BindView(R.id.linear_five)
    LinearLayout linearLayout;
    @BindView(R.id.edit_number)
    EditText editNumber;
    @BindView(R.id.back_button)
    ImageView backImage;


    String mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_submit);
        ButterKnife.bind(this);
        nextBtn.setOnClickListener(this);
        editNumber.setOnClickListener(this);
        backImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_button:
                sendOtpData();
                Toast.makeText(PhoneNumberSubmitActivity.this, "Submit", Toast.LENGTH_SHORT).show();
                break;

            case R.id.edit_number:
                linearLayout.setVisibility(View.GONE);
                break;
            case R.id.back_button:
                finish();
                break;
        }
    }

    private void sendOtpData() {


        mobile = editNumber.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            editNumber.setError(getString(R.string.invalid_mobile));
            return;
        } else {
            if (mobile.length() < 10) {
                editNumber.setError(getString(R.string.valid_phone));
                return;
            }
        }


        if (Utils.isInternetConnected(this)) {
            Utils.showProgressDialog(this);
            RestClient.verifyMobile(mobile, new Callback<VerifyMobileResponse>() {
                @Override
                public void onResponse(Call<VerifyMobileResponse> call, Response<VerifyMobileResponse> response) {
                    Utils.dismissProgressDialog();
                    Intent intent = new Intent(PhoneNumberSubmitActivity.this, VerifyOtpActivity.class);
                    intent.putExtra("MOBILE", mobile);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<VerifyMobileResponse> call, Throwable t) {
                    Utils.dismissProgressDialog();
                    Toast.makeText(PhoneNumberSubmitActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Utils.dismissProgressDialog();
            Toast.makeText(this, "Internet Connections Failed", Toast.LENGTH_SHORT).show();
        }


    }

}
