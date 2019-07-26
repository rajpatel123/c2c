package com.chalojmd.model.loginRegister;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {

@SerializedName("Mobile")
@Expose
private String mobile;
@SerializedName("Password")
@Expose
private String password;

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getPassword() {
return password;
}

public void setPassword(String password) {
this.password = password;
}

}