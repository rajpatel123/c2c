package com.e.c2cjprtechnosoft.Retrofit;


import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserRequest;
import com.e.c2cjprtechnosoft.model.udateUser.UpdateUserResponse;
import com.e.c2cjprtechnosoft.model.verifyMobile.VerifyMobileResponse;
import com.e.c2cjprtechnosoft.model.verifyOtp.VerifyOTPResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {



   @POST("mobile/{mobile}")
   Call<VerifyMobileResponse> verifyMobile(@Path("mobile") String mobile);

   @POST("verifyotp/{otp}/{mobile}")
   Call<VerifyOTPResponse> verifyOtp(@Path("otp") String otp,
                                     @Path("mobile") String mobile);


   @POST("updateuser")
   Call<UpdateUserResponse> updateUser(@Body UpdateUserRequest updateUserRequest);





   }