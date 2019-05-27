package com.chalojmd.model.verifyMobile;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyMobileResponse {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("createdOn")
@Expose
private String createdOn;
@SerializedName("updatedOn")
@Expose
private Object updatedOn;
@SerializedName("phoneNumber")
@Expose
private String phoneNumber;
@SerializedName("otp")
@Expose
private String otp;
@SerializedName("otpExpirationTime")
@Expose
private String otpExpirationTime;
@SerializedName("firstOtpRequestedOn")
@Expose
private String firstOtpRequestedOn;
@SerializedName("firstOtpValidationFailureOn")
@Expose
private Object firstOtpValidationFailureOn;
@SerializedName("firstSignupOtpValidationFailureOn")
@Expose
private Object firstSignupOtpValidationFailureOn;
@SerializedName("otpValidationFailureCount")
@Expose
private Object otpValidationFailureCount;
@SerializedName("signupOtpValidationFailureCount")
@Expose
private Object signupOtpValidationFailureCount;
@SerializedName("otpRequestCount")
@Expose
private Integer otpRequestCount;
@SerializedName("new")
@Expose
private Boolean _new;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getCreatedOn() {
return createdOn;
}

public void setCreatedOn(String createdOn) {
this.createdOn = createdOn;
}

public Object getUpdatedOn() {
return updatedOn;
}

public void setUpdatedOn(Object updatedOn) {
this.updatedOn = updatedOn;
}

public String getPhoneNumber() {
return phoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
this.phoneNumber = phoneNumber;
}

public String getOtp() {
return otp;
}

public void setOtp(String otp) {
this.otp = otp;
}

public String getOtpExpirationTime() {
return otpExpirationTime;
}

public void setOtpExpirationTime(String otpExpirationTime) {
this.otpExpirationTime = otpExpirationTime;
}

public String getFirstOtpRequestedOn() {
return firstOtpRequestedOn;
}

public void setFirstOtpRequestedOn(String firstOtpRequestedOn) {
this.firstOtpRequestedOn = firstOtpRequestedOn;
}

public Object getFirstOtpValidationFailureOn() {
return firstOtpValidationFailureOn;
}

public void setFirstOtpValidationFailureOn(Object firstOtpValidationFailureOn) {
this.firstOtpValidationFailureOn = firstOtpValidationFailureOn;
}

public Object getFirstSignupOtpValidationFailureOn() {
return firstSignupOtpValidationFailureOn;
}

public void setFirstSignupOtpValidationFailureOn(Object firstSignupOtpValidationFailureOn) {
this.firstSignupOtpValidationFailureOn = firstSignupOtpValidationFailureOn;
}

public Object getOtpValidationFailureCount() {
return otpValidationFailureCount;
}

public void setOtpValidationFailureCount(Object otpValidationFailureCount) {
this.otpValidationFailureCount = otpValidationFailureCount;
}

public Object getSignupOtpValidationFailureCount() {
return signupOtpValidationFailureCount;
}

public void setSignupOtpValidationFailureCount(Object signupOtpValidationFailureCount) {
this.signupOtpValidationFailureCount = signupOtpValidationFailureCount;
}

public Integer getOtpRequestCount() {
return otpRequestCount;
}

public void setOtpRequestCount(Integer otpRequestCount) {
this.otpRequestCount = otpRequestCount;
}

public Boolean getNew() {
return _new;
}

public void setNew(Boolean _new) {
this._new = _new;
}

}