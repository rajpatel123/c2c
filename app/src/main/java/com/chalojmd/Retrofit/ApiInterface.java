package com.chalojmd.Retrofit;


import com.chalojmd.model.TestResponse;
import com.chalojmd.model.loginRegister.LoginRequest;
import com.chalojmd.model.loginRegister.LoginResponse;
import com.chalojmd.model.otpverify.VerifyNewOtpRequest;
import com.chalojmd.model.otpverify.VerifyNewOtpResponse;
import com.chalojmd.model.registration.RegistrationRequest;
import com.chalojmd.model.registration.RegistrationResponse;
import com.chalojmd.model.udateUser.UpdateUserRequest;
import com.chalojmd.model.udateUser.UpdateUserResponse;
import com.chalojmd.model.verifyMobile.VerifyMobileResponse;
import com.chalojmd.model.verifyOtp.VerifyOTPResponse;

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

   @POST("http://vrok.in/grofer_api/list")
   Call<TestResponse>getAllRides();

   @POST("http://vrok.in/c2c_dev/login_register")
    Call<LoginResponse>loginUser(@Body LoginRequest loginRequest);

   @POST("http://vrok.in/c2c_dev/otp_verify")
   Call<VerifyNewOtpResponse>verifyOtp(@Body VerifyNewOtpRequest verifyNewOtpRequest);

   @POST("http://vrok.in/c2c_dev/update_user")
   Call<RegistrationResponse>registerUser(@Body RegistrationRequest registrationRequest);
   }