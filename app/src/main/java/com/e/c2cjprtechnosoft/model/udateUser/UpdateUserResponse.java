package com.e.c2cjprtechnosoft.model.udateUser;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateUserResponse {

@SerializedName("username")
@Expose
private String username;
@SerializedName("mobile")
@Expose
private Integer mobile;
@SerializedName("email")
@Expose
private String email;
@SerializedName("createdOn")
@Expose
private String createdOn;
@SerializedName("updatedOn")
@Expose
private String updatedOn;

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public Integer getMobile() {
return mobile;
}

public void setMobile(Integer mobile) {
this.mobile = mobile;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getCreatedOn() {
return createdOn;
}

public void setCreatedOn(String createdOn) {
this.createdOn = createdOn;
}

public String getUpdatedOn() {
return updatedOn;
}

public void setUpdatedOn(String updatedOn) {
this.updatedOn = updatedOn;
}

}