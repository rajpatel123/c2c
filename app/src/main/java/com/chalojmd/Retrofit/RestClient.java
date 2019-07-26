package com.chalojmd.Retrofit;

import com.chalojmd.model.TestResponse;
import com.chalojmd.model.loginRegister.LoginRequest;
import com.chalojmd.model.loginRegister.LoginResponse;
import com.chalojmd.model.otpverify.VerifyOtpRequest;
import com.chalojmd.model.otpverify.VerifyOtpResponse;
import com.chalojmd.model.udateUser.UpdateUserRequest;
import com.chalojmd.model.udateUser.UpdateUserResponse;
import com.chalojmd.model.verifyMobile.VerifyMobileResponse;
import com.chalojmd.model.verifyOtp.VerifyOTPResponse;

import retrofit2.Callback;


public class RestClient {


    public static void verifyMobile(String mobile, Callback<VerifyMobileResponse> callback) {
        RetrofitClient.getClient().verifyMobile(mobile).enqueue(callback);
    }


    public static void verifyOtp(String otp, String mobile, Callback<VerifyOTPResponse> callback) {
        RetrofitClient.getClient().verifyOtp(otp, mobile).enqueue(callback);
    }

    public static void updateUser(UpdateUserRequest updateUserRequest, Callback<UpdateUserResponse> callback) {
        RetrofitClient.getClient().updateUser(updateUserRequest).enqueue(callback);
    }

    public static void myAllRides( Callback<TestResponse> callback) {
        RetrofitClient.getClient().getAllRides().enqueue(callback);
    }

    public static void loginNewUser(LoginRequest loginRequest, Callback<LoginResponse> callback) {
        RetrofitClient.getClient().loginUser(loginRequest).enqueue(callback);
    }

    public static void verifyByOtp(VerifyOtpRequest verifyOtpRequest, Callback<VerifyOtpResponse> callback) {
        RetrofitClient.getClient().verifyOtp(verifyOtpRequest).enqueue(callback);
    }

}
