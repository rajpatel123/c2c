package com.chalojmd.Retrofit;


import com.chalojmd.model.udateUser.UpdateUserRequest;
import com.chalojmd.model.udateUser.UpdateUserResponse;
import com.chalojmd.model.verifyMobile.VerifyMobileResponse;
import com.chalojmd.model.verifyOtp.VerifyOTPResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {



   @GET("mobile/{mobile}")
   Call<VerifyMobileResponse> verifyMobile(@Path("mobile") String mobile);

   @POST("verifyotp/{otp}/{mobile}")
   Call<VerifyOTPResponse> verifyOtp(@Path("otp") String otp,
                                     @Path("mobile") String mobile);


   @POST("updateuser")
   Call<UpdateUserResponse> updateUser(@Body UpdateUserRequest updateUserRequest);





   }