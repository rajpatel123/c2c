package com.chalojmd.activitis.rider;

import android.content.Intent;
import android.os.Handler;
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
import com.chalojmd.activitis.NavigationActivity;
import com.chalojmd.model.loginRegister.LoginRequest;
import com.chalojmd.model.loginRegister.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRegisterActivity extends AppCompatActivity {
    private ImageView imageView;
    private EditText userPhoneNumber, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);
        userPhoneNumber = findViewById(R.id.phone_number);
        password = findViewById(R.id.password);
        imageView = findViewById(R.id.next_button);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndloginUser();

            }
        });
    }

    public void validateAndloginUser() {

        boolean check = true;
        String phone_Number = userPhoneNumber.getText().toString();
        String user_Password = password.getText().toString();

        if (phone_Number.isEmpty() || userPhoneNumber.length() < 4 || userPhoneNumber.length() > 10) {
            userPhoneNumber.setError("enter a valid email address");
            check = false;
        } else {
            userPhoneNumber.setError(null);
        }

        if (user_Password.isEmpty() || password.length() < 4 || password.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            check = false;
        } else {
            password.setError(null);
        }
        if (check) {

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setMobile(phone_Number);
            loginRequest.setPassword(user_Password);

            Utils.showProgressDialog(this);
            RestClient.loginNewUser(loginRequest, new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.body().getUserStatus().equalsIgnoreCase("1") ) {
                                Intent i = new Intent(LoginRegisterActivity.this, NavigationActivity.class);
                                Toast.makeText(LoginRegisterActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();



                                startActivity(i);
                                finish();
                            } else if (response.body().getUserStatus().equalsIgnoreCase("0")){
                                Intent i = new Intent(LoginRegisterActivity.this, OtpVerifyActivity.class);
                                startActivity(i);
                                String  mobileNumber =response.body().getMobile();
                                String id = response.body().getId();
                                C2CPref.putString(getApplicationContext(), "mobilenumber", mobileNumber);
                                C2CPref.putString(getApplicationContext(), "user_id",id);
                                Toast.makeText(LoginRegisterActivity.this, "you are not Register", Toast.LENGTH_SHORT).show();
                                finish();
                            }


                        }
                    }



                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(LoginRegisterActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            });


        }


    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

}
