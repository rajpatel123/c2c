package com.e.c2cjprtechnosoft.Retrofit;

import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserRequest;
import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserResponse;
import com.e.c2cjprtechnosoft.model.verifyMobile.VerifyMobileResponse;
import com.e.c2cjprtechnosoft.model.verifyOtp.VerifyOTPResponse;

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


}
