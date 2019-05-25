package com.e.c2cjprtechnosoft.model.verifyOtp;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOTPResponse {

@SerializedName("username")
@Expose
private Object username;
@SerializedName("mobile")
@Expose
private Integer mobile;
@SerializedName("email")
@Expose
private Object email;
@SerializedName("createdOn")
@Expose
private String createdOn;
@SerializedName("updatedOn")
@Expose
private Object updatedOn;

public Object getUsername() {
return username;
}

public void setUsername(Object username) {
this.username = username;
}

public Integer getMobile() {
return mobile;
}

public void setMobile(Integer mobile) {
this.mobile = mobile;
}

public Object getEmail() {
return email;
}

public void setEmail(Object email) {
this.email = email;
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

}