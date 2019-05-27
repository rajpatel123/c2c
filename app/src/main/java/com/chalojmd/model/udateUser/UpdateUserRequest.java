package com.chalojmd.model.udateUser;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserRequest {

@SerializedName("username")
@Expose
private String username;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("email")
@Expose
private String email;

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

}